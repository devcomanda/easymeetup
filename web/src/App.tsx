import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import { Route, Switch } from 'react-router-dom';
import Base from './components/Base/Base';
import AdminCms from './components/Admin-cms/AdminCms';
import Events from './components/Events/Events';
import './App.css';
import Login from './components/Auth/Login';
import Registration from './components/Auth/Registration';

class App extends Component {
  render() {
    return (
      <Switch>
        <Route path="/events/" component={Events} />
        <Route path="/cms" component={AdminCms}/>
        <Route path="/login" component={Login}/>
        <Route path="/registration" component={Registration}/>
        <Route exact path="/" component={Base}/>
      </Switch>
    );
  }
}

export default App;
