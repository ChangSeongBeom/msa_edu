

import React from 'react';
import Portal from "./Portal";
import { DndProvider } from "react-dnd";
import Backend from "react-dnd-html5-backend";

import styles from "./index.module.css";

function Index() {
  return (
    <div className={styles.index}>
      <DndProvider backend={Backend}>
        <Portal />
      </DndProvider>
    </div>
  );
}

export default Index