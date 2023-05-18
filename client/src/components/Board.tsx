import { useState, useEffect } from "react"
import "./Board.css"
import Tile from "./Tile"
import { useParams } from "react-router-dom";
import Swal from "sweetalert2";
import PlayerData from "./PlayerData";

interface GameData {
    gameId: number,
    boardState: number[][],
    playerTurn: number,
}

const Phase = Object.freeze({
    PLACEMENT: 0,
    MOVEMENT: 1,
    REMOVE: 2
})

const Board = () => {
    // End Game
    const [gameOver, setGameOver] = useState(false)

    // Use state variables for determining the display and movement
    const [boardState, setBoardState] = useState<number[][]>([]);
    const [playerTurn, setPlayerTurn] = useState(1) // By default set to 1 unless changed
    const [playerPhase, setPlayerPhase] = useState(0)

    // Player state variables
    const [playerOneName, setPlayerOneName] = useState("");
    const [playerTwoName, setPlayerTwoName] = useState("");
    const [playerOneTokensLeft, setPlayerOneTokensLeft] = useState("");
    const [playerTwoTokensLeft, setPlayerTwoTokensLeft] = useState("");
    const [playerOneTokensStorage, setPlayerOneTokensStorage] = useState("");
    const [playerTwoTokensStorage, setPlayerTwoTokensStorage] = useState("");

    // Piece for movement
    const [selectedPiece, setSelectedPiece] = useState<{ row: number, col: number } | null>(null)

    const { gameId } = useParams(); // This is for identification of the game 
    
    const initialiseStates = async () => {
        // Get Board from Backend
        try {
            // Default board in case backend is not online
            const board =  [[1,0,0,0,0,0,0],
                            [0,2,0,1,0,1,0],
                            [0,0,1,1,1,0,0],
                            [1,1,1,0,1,3,1],
                            [0,0,3,1,1,0,0],
                            [0,2,0,3,0,2,0],
                            [2,0,0,1,0,0,1],]

            // This is for the local hosted vs                             
            const fetchLocation = 'http://localhost:9999/initfromid'
            //const fetchLocation = 'http://170.64.176.243/initfromid'

            const response = await fetch(fetchLocation, {
                method: 'POST',
                headers: {
                  'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    "game_id": Number(gameId),
                })
            })
           
            //if backend is not online
            if (!response.ok) {
                setBoardState(board)
            } else {
                const json = await response.json()
                setBoardState(json.board)
                setPlayerTurn(Number(json.player) - 1)
                console.log(Number(json.phase))
                setPlayerPhase(Number(json.phase))
                setGameOver(json.game_status)
            }
        } catch (error) {
        }
    }

    const initialisePlayerStates = async() => {
        try {
            // This is for the local hosted vs                             
            const fetchLocation = 'http://localhost:9999/initplayerdata'
            //const fetchLocation = 'http://170.64.176.243/initplayerdata'

            const response = await fetch(fetchLocation, {
                method: 'POST',
                headers: {
                  'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    "game_id": Number(gameId),
                })
            })
            if (response.ok) {
                const json = await response.json()
                setPlayerOneName(json.player_one_name)
                setPlayerTwoName(json.player_two_name)
                setPlayerOneTokensLeft(json.player_one_token_count)
                setPlayerTwoTokensLeft(json.player_two_token_count)
                setPlayerOneTokensStorage(json.player_one_token_storage)
                setPlayerTwoTokensStorage(json.player_two_token_storage)
            }
        } catch (error) {
            
        }
    }

    const handleDragStart = (row: number, col: number) => {
        if (playerPhase == Phase.MOVEMENT) {
            // Handelling the start of dragg piece
            setSelectedPiece({ row, col })
        }
        else {
            alertHelper("Cant move!")
        }
    }

    const handleDragOver = (event: React.DragEvent<HTMLDivElement>) => {
        event.preventDefault()
    }

    const handleDrop = async(event: React.DragEvent<HTMLDivElement>, row: number, col: number) => {
        event.preventDefault()
        if (selectedPiece) {
            const { row: fromRow, col: fromCol } = selectedPiece
      
            // Creates a new temporary board dep copy
            const tempBoard = JSON.parse(JSON.stringify(boardState))
            tempBoard[row][col] = tempBoard[fromRow][fromCol]
            // Assumption previous checks are all correct
            tempBoard[fromRow][fromCol] = 1
            
            // Make the fetch request and wait for status
            // Should return false if not players turn
            // if placed on not good piece
            if (await handleMove([fromRow, fromCol], [row, col])) {
                playerHelper()
                setBoardState(tempBoard)
            }
            else {
                alertHelper("Invalid Move")
            }
        }
        setSelectedPiece(null)
    }

    const handleMove = async(originalPosition: Array<Number>, movementPosition: Array<Number>) => {
        console.log("FIRST")
        console.log(originalPosition[0])
        console.log(originalPosition[1])
        console.log("SECOND")
        console.log(movementPosition[0])
        console.log(movementPosition[1])
        let validMove = false

        // This is for the local hosted vs                             
        const fetchLocation = 'http://localhost:9999/makemove'
        //const fetchLocation = 'http://170.64.176.243/makemove'

        const response = await fetch(fetchLocation, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "original_row": Number(originalPosition[0]),
                "original_col": Number(originalPosition[1]),
                "movement_row": Number(movementPosition[0]),
                "movement_col": Number(movementPosition[1]),
                "game_id": gameId
            })
        })

        console.log(response.ok)
        if (response.ok) {
            validMove = await response.json();
        } else {
            console.log("Error:", response.statusText);
        }
        initialiseStates()
        initialisePlayerStates()
        return(validMove)
    }

    /**
     * This is for handling 
     * @param row 
     * @param col 
     */
    const handleClickMove = async (row: number, col: number) => {
        
        if (playerPhase != Phase.MOVEMENT) {
            console.log(row)
            console.log(col)

            if (playerPhase == Phase.PLACEMENT) {
                if (!(await handleMove([0, 0], [row, col]))) {
                    alertHelper("Position is not empty!")
                }
            }
            else if (playerPhase == Phase.REMOVE) {
                if (!(await handleMove([row, col], [0, 0]))) {
                    alertHelper("Invalid Removal")
                }
            }

            // reinitialising
            initialiseStates()
            initialisePlayerStates()
        }

    }

    /**
     * Player Swapper Helper Function
     */
    const playerHelper = () => {
        if (playerTurn == 1) {
            setPlayerTurn(2)
        }
        else {
            setPlayerTurn(1)
        }
    }

    /**
     * Alert Message Helper Function
     */
    const alertHelper = (message: string) => {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: message, 
            timer: 1000,
            timerProgressBar: true,
          })
    }

    const gameOverHelper = () => {
        Swal.fire({
            icon: 'success',
            title: 'Oops...',
            text: "Game is Over", 
            timer: 1000,
            timerProgressBar: true,
          })
    }

    useEffect(() => {
        initialiseStates()
        initialisePlayerStates()
    }, [])

    return (
    <section className="main-game">
        {gameOver ? 
            <section className="section-board">
            <article className="article-player-container">
                <h2>Player {playerTurn == 1 ? "White": "Black"} Has Won</h2>
            </article>
            <article className="ariticle-board-container">
                {boardState.map((row, rowIndex) => {
                    return (<div key={rowIndex}>
                        <div className="article-board" >{row.map((col, colIndex) => {
                            return(
                                <div 
                                key={colIndex}
                                onClick={() => gameOverHelper()}
                                onDragStart={() => gameOverHelper()}
                                >
                                    <Tile tile={col}/>
                                </div>
                            )
                        })}</div>
                    </div>
                    )
                })}
            </article>
        </section> 
        : <section className="section-board">
            <article className="article-player-container">
                <h2>Player {playerTurn == 1 ? "White": "Black"}'s Turn to {playerPhase == 0 ? "Place": playerPhase == 1 ? "Move" : "Remove"}</h2>
            </article>
            <article className="ariticle-board-container">
                {boardState.map((row, rowIndex) => {
                    return (<div key={rowIndex}>
                        <div className="article-board" >{row.map((col, colIndex) => {
                            return(
                                <div 
                                key={colIndex}
                                onClick={() => handleClickMove(rowIndex, colIndex)}
                                onDragStart={() => handleDragStart(rowIndex, colIndex)}
                                onDragOver={(event) => handleDragOver(event)}
                                onDrop={(event) => handleDrop(event, rowIndex, colIndex)}
                                >
                                    <Tile tile={col}/>
                                </div>
                            )
                        })}</div>
                    </div>
                    )
                })}
            </article>
        </section>
        }
        <PlayerData playerOneName={playerOneName} playerTwoName={playerTwoName} playerOneTokens={playerOneTokensLeft} playerTwoTokens={playerTwoTokensLeft} playerOneStorage={playerOneTokensStorage} playerTwoStorage={playerTwoTokensStorage} />
    </section>
  )
}

export default Board