

import React from 'react';
import Example from "./Example";
import { DndProvider } from "react-dnd";
import Backend from "react-dnd-html5-backend";

import styles from "./index.module.css";

function Index() {
  return (
    <div className={styles.index}>
      <DndProvider backend={Backend}>
        <Example />
      </DndProvider>
    </div>
  );
}

export default Index