import { useState, useEffect } from "react"
import "./Board.css"
import Tile from "./Tile"


const Board = () => {
    const [gameId, setGameId] = useState(0);
    const [boardState, setBoardState] = useState<number[][]>([]);
    const [playerTurn, setPlayerTurn] = useState(1) // By default set to 1 unless changed
    const [selectedPiece, setSelectedPiece] = useState<{ row: number, col: number } | null>(null)

    const initialiseStates = async () => {
        // Get Board from Backend
        try {
            // Default board in case backend is not online
            const board =  [[1,0,0,2,0,0,3],
                            [0,2,0,1,0,1,0],
                            [0,0,1,1,1,0,0],
                            [1,1,1,0,1,3,1],
                            [0,0,3,1,1,0,0],
                            [0,2,0,3,0,2,0],
                            [2,0,0,1,0,0,1],]
            // Attempts to populate board                          
            const response = await fetch('http://localhost:9999/initgame', {
                method: 'GET',
                headers: {
                  'Content-Type': 'application/json'
                },
            })

            //if backend is not online
            if (!response) {
                setBoardState(board)
            } else {
                const json = await response.json();
                // setBoardState(json.board)
                setGameId(json.game_id)
                setBoardState(json.board.boardState)
            }
        } catch (error) {
        }
    }

    const handleDragStart = (row: number, col: number) => {
        // Handelling the start of dragg piece
        setSelectedPiece({ row, col })
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
            tempBoard[fromRow][fromCol] = 1
    
            // Make the fetch request and wait for status
            // Should return false if not players turn
            // if placed on not good piece
            if (await handleMove(tempBoard)) {
                playerHelper()
                setBoardState(tempBoard)
            }
            else {
                alert("Invalid Move")
            }
        }
        setSelectedPiece(null)
    }

    const handleMove = async(tempBoard: any) => {
        let validMove = false
        const response = await fetch('http://localhost:9999/makemove', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "board": tempBoard,
                "game_id": gameId
            })
        })
        if (response.ok) {
            validMove = await response.json();
        } else {
            console.log("Error:", response.statusText);
        }
        return(validMove)
    }

    const playerHelper = () => {
        if (playerTurn == 1) {
            setPlayerTurn(2)
        }
        else {
            setPlayerTurn(1)
        }
    }

    useEffect(() => {
        initialiseStates()
    }, [])

  return (
    <section className="section-board">
        <article className="article-player-container">
            <h2>Player {playerTurn}'s Turn</h2>
        </article>
        <article className="ariticle-board-container">
            {boardState.map((row, rowIndex) => {
                return (<div key={rowIndex}>
                    <div className="article-board" >{row.map((col, colIndex) => {
                        return(
                            <div 
                            key={colIndex}
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
  )
}

export default Board