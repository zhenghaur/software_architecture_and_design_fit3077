import { Link } from "react-router-dom"
import "./Menu.css"

interface MenuProp {
    link: string;
}

const Menu = (props: MenuProp) => {
    return (
        <section className="section-menu">
            <h1> Nine Man Morris </h1>
            <ul>
                <Link className="link-left-navbar" to={props.link}>
                    <li>Start Game</li>
                </Link>
            </ul>
        </section>
    )
}

export default Menu