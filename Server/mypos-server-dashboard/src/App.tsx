import './App.css';
import  Layout  from './Layout';
import Items from './Items';
import { BrowserRouter, Routes, Route } from 'react-router-dom'

function App() {

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<h1>Home</h1>}/>
            <Route path="items" element={<Items />}/>
          </Route>
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
