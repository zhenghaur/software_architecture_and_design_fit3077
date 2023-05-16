import "./PlayerData.css"

interface PlayerData {
    playerOneName: string,
    playerTwoName: string,
    playerOneTokens: string,
    playerTwoTokens: string,
    playerOneStorage: string,
    playerTwoStorage: string,
}

const PlayerData = (props: PlayerData) => {
    return(
        <>  
            <section className="section-playerdata">
                <article className="article-player">
                    <div className="grid-container">
                        <div></div>
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
                        <p>Not working</p>
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