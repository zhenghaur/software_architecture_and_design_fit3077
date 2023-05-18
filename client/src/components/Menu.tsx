import { Link } from "react-router-dom"
import "./Menu.css"
import { useEffect, useState } from "react";

interface MenuProp {
    link: string;
}

const Menu = (props: MenuProp) => {
    const [gameId, setGameId] = useState(0);

    const initialiseGame = async () => {
        // This is for the local hosted vs                             
        const fetchLocation = 'http://localhost:9999/initgame'
        //const fetchLocation = 'http://170.64.176.243/initgame'

        const response = await fetch(fetchLocation, {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json'
            },
        })
        const json = await response.json();
        setGameId(Number(json.game_id))
    }

    useEffect(() => {
        initialiseGame()
      }, []);
    
    return (
        <section className="section-menu">
            <h1> Nine Man Morris </h1>
            <ul>
                <Link className="link-left-navbar" to={`${props.link}/${gameId}`}>
                    <li>Start Game</li>
                </Link>
            </ul>
        </section>
    )
}

export default Menu