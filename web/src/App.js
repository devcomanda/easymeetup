import React, { Component } from 'react';
import './App.css';
import Event from  './components/event/Event.js';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

class App extends Component {
  render() {
    return (
      <Router>
        <div>
          <nav>
            <ul>
              <li>
                <Link to="/event/">Event</Link>
              </li>
            </ul>
          </nav>

          <Route path="/event/" component={Event} />
        </div>
      </Router>
    );
  }
}

export default App;
