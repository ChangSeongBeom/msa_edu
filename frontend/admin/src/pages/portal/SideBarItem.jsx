import React from "react";
import { useDrag } from "react-dnd";
import styles from "./index.module.css";

const SideBarItem = ({ data }) => {
  const [{ opacity }, drag] = useDrag({
    item: data,
    collect: monitor => ({
      opacity: monitor.isDragging() ? 0.4 : 1
    })
  });
  
  return (
    <div className={styles.sideBarItem}ref={drag} style={{ opacity }}>
      {data.component.type}
    </div>
  );
};
export default SideBarItem;
