import React, { useRef } from "react";
import { useDrag } from "react-dnd";
import { COMPONENT } from "./constants";
import styles from "./index.module.css";


const style = {
  border: "1px dashed black",
  padding: "0.5rem 1rem",
  backgroundColor: "white",
  cursor: "move"
};
const Component = ({ data, components, path }) => {
  console.log("component data",data);
  const ref = useRef(null);

  const [{ isDragging }, drag] = useDrag({
    item: { type: COMPONENT, id: data.id, path },
    collect: monitor => ({
      isDragging: monitor.isDragging()
    })
  });

  const opacity = isDragging ? 0 : 1;
  drag(ref);
  
  
  // const component = components[data.id];
  // if (!component) {
  //   return null; // Return null or some placeholder content if the component is not found
  // }

  
  return (
    <div
      ref={ref}
      style={{ ...style, opacity }}
      className={`${styles.component} ${styles.draggable} `}
      //className="component draggable"
    >
      <div>{data.id}</div>
      <div>{data.content}</div>
    </div>
  );
};
export default Component;
