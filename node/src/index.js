import React from "react";
//import { render } from "react-dom";
import * as ReactDOMClient from 'react-dom/client';
import Router from "./Router";
import { BrowserRouter } from "react-router-dom";

// React 18 has a new API for creating root.
const root = ReactDOMClient.createRoot(document.getElementById('root'));
root.render(<BrowserRouter><Router /></BrowserRouter>);

