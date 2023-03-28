import React from "react";
import { Routes, Route } from "react-router-dom";
import {Books} from "./routes/root/Books";
import {BookDetails} from "./routes/books/BookDetails";
import {Checkout} from "./routes/checkout/Checkout";

function Router() {
    return (
        <div>
            <Routes>
                <Route path="/" element={<Books />} />
                <Route path="books/:id" element={<BookDetails />} />
                <Route path="checkout/:id" element={<Checkout />} />
            </Routes>
        </div>
    );
}

export default Router;