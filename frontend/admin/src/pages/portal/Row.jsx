import React, { useRef } from "react";
import { useDrag } from "react-dnd";
import { ROW } from "./constants";
import DropZone from "./DropZone";
import Column from "./Column";
import styles from "./index.module.css";

const style = {};
const Row = ({ data, components, handleDrop, path }) => {
  const ref = useRef(null);

  const [{ isDragging }, drag] = useDrag({
    item: {
      type: ROW,
      id: data.id,
      children: data.children,
      path
    },
    collect: monitor => ({
      isDragging: monitor.isDragging()
    })
  });

  const opacity = isDragging ? 0 : 1;
  drag(ref);

  const renderColumn = (column, currentPath) => {
    return (
      <Column
        key={column.id}
        data={column}
        components={components}
        handleDrop={handleDrop}
        path={currentPath}
      />
    );
  };

  return (
    <div ref={ref} 
    style={{ ...style, opacity }} 
    className={`${styles.base} ${styles.draggable} ${styles.row}`}>
      {data.id}
      <div className={styles.columns}>
        {data.children.map((column, index) => {
          const currentPath = `${path}-${index}`;

          return (
            <React.Fragment key={column.id} >
              <DropZone
                data={{
                  path: currentPath,
                  childrenCount: data.children.length,
                }}
                onDrop={handleDrop}
                className={`${styles.horizontalDrag}`}


              />
              {renderColumn(column, currentPath)}
            </React.Fragment>
          );
        })}
        <DropZone
          data={{
            path: `${path}-${data.children.length}`,
            childrenCount: data.children.length
          }}
          onDrop={handleDrop}
          className={`${styles.horizontalDrag}`}
         
        />
      </div>
    </div>
  );
};
export default Row;
