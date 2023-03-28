import React from "react";
import { Routes, Route } from "react-router-dom";
import {Books} from "./routes/books/Books";
import {Copies} from "./routes/copies/Copies";

function Router() {
    return (
        <div>
            <Routes>
                <Route path="/" element={<Books />} />
                <Route path="copies/:id" element={<Copies />} />
            </Routes>
        </div>
    );
}

export default Router;