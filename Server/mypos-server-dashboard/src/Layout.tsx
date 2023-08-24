import { Link, Outlet } from 'react-router-dom';

function Layout() {
    return(
        <>
            <div>
                <ul>
                    <li><Link to="/">Home</Link></li>
                    <li><Link to="/items">Items</Link></li>
                    <li><Link to="/users">Users</Link></li>
                    <li><Link to="/transactions">Transactions</Link></li>
                    <li><Link to="/profile">Profile</Link></li>   
                </ul>
            </div>
            <Outlet />
        </>
        
    )
}

export default Layout;