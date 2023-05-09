import { Route, RouterProvider, createBrowserRouter, createRoutesFromElements } from 'react-router-dom'
import './App.css'
import Main from  './pages/Main'
import Game from './pages/Game'

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/">
      {/* <Route index element={<Home />}/> */}
      <Route index element={<Main />} />
      <Route path="menu" element={<Main />}/>
      <Route path="game/:gameId" element={<Game />}/>
    </Route>), {
      basename: ""
    }
)

function App() {

  return (
    <div className="App">
      <RouterProvider router={router} />
    </div>
  )
}

export default App