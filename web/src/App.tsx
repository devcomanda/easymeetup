import React, { Component } from 'react';
import { Route, Switch } from 'react-router-dom';
import Base from './base/Base';
import AdminCms from './admin-cms/AdminCms';
import Events from './components/events/Events';
import './App.css';
import Login from './components/registration/Login'

class App extends Component {
  render() {
    return (
      <Switch>
          <Route path="/events/" component={Events} />
          <Route path="/cms" component={AdminCms}/>
          <Route path="/login" component={Login}/>
          <Route path="/" component={Base}/>
      </Switch>
    );
  }
}

export default App;
