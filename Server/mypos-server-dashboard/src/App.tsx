import './App.css';
import  Layout  from './Layout';
import Items from './Item/Items';
import { Routes, Route, Navigate } from 'react-router-dom'
import Users from './User/Users';
import Profile from './Profile';

function App() {

  return (
    <>
      <Routes>
        <Route path="index.html" element={<Navigate to="/" />} />

        <Route path="/" element={<Layout />}>
          <Route index element={<h1>Home</h1>}/>
          <Route path="items" element={<Items />}/>
          <Route path="users" element={<Users />}/>
          <Route path="profile" element={<Profile />}/>
        </Route>
      </Routes>
    </>
  )
}

export default App
