import "./Tiles.css"
import blackPiece from "../assets/circle-black-geometric-shape-svgrepo-com.svg"
import whitePiece from "../assets/circle-white-geometric-shape-svgrepo-com.svg"
import dotSpot from "../assets/dot-small-svgrepo-com.svg"
interface Tile {
    tile: number
}


const Tile = (props: Tile) => {
    return (
        <div className="div-tile-container">
           
            {props.tile == 1 && <img className="null-piece" src={dotSpot}></img>}
            {props.tile == 2 && <img className="tile-piece" src={whitePiece}></img>}
            {props.tile == 3 && <img className="tile-piece" src={blackPiece}></img>}
        </div>
    )
}

export default Tile