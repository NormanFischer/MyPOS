import { Link } from 'react-router-dom';

function Layout() {
    return(
        <div>
            <li>
                <Link to="/">Home</Link>
                <Link to="/items">Items</Link>
                <Link to="/users">Users</Link>
                <Link to="/profile">Profile</Link>    
            </li>
        </div>
    )
}

export default Layout;