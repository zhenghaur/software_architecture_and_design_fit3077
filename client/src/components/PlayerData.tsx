import "./PlayerData.css"
import undo from "../assets/undo.png"

interface PlayerData {
    playerOneName: string,
    playerTwoName: string,
    playerOneTokens: string,
    playerTwoTokens: string,
    playerOneStorage: string,
    playerTwoStorage: string,
    undoClick: () => void,
}

const PlayerData = (props: PlayerData) => {
    return(
        <>  
            <section className="section-playerdata">
                <article className="article-player">
                    <div className="grid-container">
                        <div className="undo-div" onClick={props.undoClick}>Undo<img className="undo" src={undo}></img></div>
                        <div className="grid-header div-center">White</div>
                        <div className="grid-header div-center">Black</div>
                        
                        <div>Player Name</div>
                        <div className="div-center">{props.playerOneName}</div>
                        <div className="div-center">{props.playerTwoName}</div>
                        
                        <div>Tokens Left</div>
                        <div className="div-center">{props.playerOneTokens}</div>
                        <div className="div-center">{props.playerTwoTokens}</div>
                        
                        <div>Tokens in Storage</div>
                        <div className="div-center">{props.playerOneStorage}</div>
                        <div className="div-center">{props.playerTwoStorage}</div>
                    </div>
                </article>
                <section className="message-section">
                    <article className="message-history">
                        <p>Not Implemented</p>
                    </article>
                    <div className="message-input-container">
                        <input type="text" className="message-input" placeholder="Type your message"/>
                        <button className="send-button">Send</button>
                    </div>
                </section>
            </section>
        </>
    )
}

export default PlayerData