import React from 'react';
import { createRoot } from 'react-dom/client';
import {
  BrowserRouter as Router,
  Routes,
  Route
} from "react-router-dom";

import reportWebVitals from './reportWebVitals';

import './index.css';
import './css/button.css';
import './css/header.css';
import './css/general.css';
import './css/home.css';
import './css/overview.css';
import './css/map.css';

import Home from './views/Home';
import Overview from './views/OverView';
import Header from './component/Header';
import UnknownedPage from './component/UnknownedPage';

const container = document.getElementById('root');
const root = createRoot(container);

root.render(
    <Router>
      <Header />
      <div className='content'>
        <Routes>
          <Route exact path="/" element={ <Home /> }/>
          <Route path="/overview" element={<Overview />} />
          <Route path="/*" element={ <UnknownedPage /> }/>
        </Routes>
      </div>
    </Router>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
