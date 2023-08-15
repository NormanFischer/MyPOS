import './App.css';
import  Layout  from './Layout';
import Items from './Items';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'

function App() {

  return (
    <>
      <Routes>
        <Route path="index.html" element={<Navigate to="/" />} />

        <Route path="/" element={<Layout />}>
          <Route index element={<h1>Home</h1>}/>
          <Route path="items" element={<Items />}/>
        </Route>
      </Routes>
    </>
  )
}

export default App
