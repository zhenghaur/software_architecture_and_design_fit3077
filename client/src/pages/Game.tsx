import Board from "../components/Board"

const Game = () => {

    const board = [[1,0,0,2,0,0,1],
                   [0,1,0,1,0,1,0],
                   [0,0,1,1,3,0,0],
                   [1,1,1,0,1,1,1],
                   [0,0,1,1,1,0,0],
                   [0,1,0,1,0,1,0],
                   [1,0,0,1,0,0,1],]

    return (
        <>
            <Board />
        </>
    )
}

export default Game