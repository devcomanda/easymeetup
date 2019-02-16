import React, { Component } from 'react';
import { Route, Switch } from 'react-router-dom';
import Base from './base/Base';
import AdminCms from './admin-cms/AdminCms';

class App extends Component {
  render() {
    return (
      <Switch>
          <Route path="/cms" component={AdminCms}/>
          <Route path="/" component={Base}/>
      </Switch>
    );
  }
}

export default App;
