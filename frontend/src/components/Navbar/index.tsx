import Button from 'components/Button';
import './style.css';

function Navbar() {
  return (
    <div className="navbar-container">
      <nav className="navbar-content">
        <label className="navbar-label">Movieflix</label>
      </nav>
      <Button /> 
    </div>
  );
}

export default Navbar;
