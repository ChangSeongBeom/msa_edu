import React, { useState, useCallback, useEffect } from "react";

import SaveZone from "./SaveZone";
import DropZone from "./DropZone";
import TrashDropZone from "./TrashDropZone";
import SideBarItem from "./SideBarItem";
import Row from "./Row";
import axios from "axios";
import initialData from "./initial-data";
import { REFRESH_TOKEN, ACCESS_TOKEN, CLAIM_NAME, SERVER_API_URL } from '@constants/env';
import { common, statisticsService } from '@service';



import {
  handleMoveWithinParent,
  handleMoveToDifferentParent,
  handleMoveSidebarComponentIntoParent,
  handleRemoveItemFromLayout
} from "./helpers";
import useUser from '@hooks/useUser'

import { SIDEBAR_ITEMS, SIDEBAR_ITEM, COMPONENT, COLUMN } from "./constants";
import shortid from "shortid";
import styles from "./index.module.css";

function Example() {
  const initialLayout = initialData.layout;
  const initialComponents = initialData.components;
  const [components, setComponents] = useState(initialComponents);

  const MESSAGE_URL = `${SERVER_API_URL}/portal-service/api/v1/getPortals/1`;
  const { user, loading, isLogin, loggedOut } = useUser()
  const [dataLayout, setDataLayout] = useState([]);

  const [lastPK, setLastPK] = useState();
  const [layout, setLayout] = useState(initialLayout);
  const [newItems, setNewItems] = useState([]);
  useEffect(() => {
    if (isLogin) {
      axios.get(`/portal-service/api/v1/getPortals/1`, {
        headers: common.headers,
      })
        .then(function (response) {
          setDataLayout(response.data);

        })
        .catch(error => {
          console.error('Error fetching data:', error);
        });
    }
  }, [isLogin]);


  useEffect(() => {
    setLayout(dataLayout);
    console.log("layout",layout);
  }, [dataLayout]);

  useEffect(() => {
    console.log("Updated layout:", layout);
  }, [layout]);



  useEffect(() => {
    if (isLogin) {
      axios.get(`/portal-service/api/v1/portals/lastPK`, {
        headers: common.headers,
      })
        .then(function (response) {
          const lastPKValue = response.data;
          const newLastPKValue = lastPKValue + 1;

          setLastPK(newLastPKValue);

        })
        .catch(error => {
          console.error('Error fetching data:', error);
        });
    }
  }, [isLogin]);


  useEffect(() => {
    console.log('Updated newItems:', newItems);
  }, [newItems]);


  const handleDropToTrashBin = useCallback(
    (dropZone, item) => {
      const splitItemPath = item.path.split("-");
      setLayout(handleRemoveItemFromLayout(layout, splitItemPath));
    },
    [layout]
  );

  const handleDrop = useCallback(
    (dropZone, item) => {
      const newItem = { id: item.id, type: item.type, component:item.component };
      setNewItems(prevItems => [...prevItems, newItem]);
     
      setLastPK(lastPK + 1);

      const splitDropZonePath = dropZone.path.split("-");
      const pathToDropZone = splitDropZonePath.slice(0, -1).join("-");

      
      if (item.type === COLUMN) {
        newItem.children = item.children;
      }


      // sidebar into
      if (item.type === SIDEBAR_ITEM) {
        // 1. Move sidebar item into page
        const newComponent = {
          id: lastPK,
          ...item.component
        };
        const newItem = {
          id: newComponent.id,
          type: COMPONENT
        };
        // setComponents(prevComponents => ({
        //   ...prevComponents,
        //   [newComponent.id]: newComponent
        // }));

        setComponents({
          ...components,
          [newComponent.id]: newComponent
        });
        setLayout(
          handleMoveSidebarComponentIntoParent(
            layout,
            splitDropZonePath,
            newItem,
            lastPK
          )
        );

        // setLastPK(lastPK+1);
        return;

      }

      // move down here since sidebar items dont have path
      const splitItemPath = item.path.split("-");
      const pathToItem = splitItemPath.slice(0, -1).join("-");

      // 2. Pure move (no create)
      if (splitItemPath.length === splitDropZonePath.length) {
        // 2.a. move within parent
        if (pathToItem === pathToDropZone) {
          setLayout(
            handleMoveWithinParent(layout, splitDropZonePath, splitItemPath)
          );

          return;
        }

        // 2.b. OR move different parent
        // TODO FIX columns. item includes children
        setLayout(
          handleMoveToDifferentParent(
            layout,
            splitDropZonePath,
            splitItemPath,
            newItem,
            lastPK
          )
        );

        return;

      }

      // 3. Move + Create
      setLayout(
        handleMoveToDifferentParent(
          layout,
          splitDropZonePath,
          splitItemPath,
          newItem
        )
      );

    },
    [layout, components]
  );

  const renderRow = (row, currentPath) => {
    return (
      <Row
        key={row.id}
        data={row}
        handleDrop={handleDrop}
        components={components}
        path={currentPath}
      />
    );
  };

  // dont use index for key when mapping over items
  // causes this issue - https://github.com/react-dnd/react-dnd/issues/342
  return (
    <div className={styles.body}>
      <div className={styles.sideBar}>
        {Object.values(SIDEBAR_ITEMS).map((sideBarItem, index) => (
          <SideBarItem key={sideBarItem.id} data={sideBarItem} />
        ))}
      </div>
      <div className={styles.pageContainer}>
        <div className={styles.page}>
          {layout.map((row, index) => {
            const currentPath = `${index}`;

            return (
              <React.Fragment key={row.id}>
                <DropZone
                  data={{
                    path: currentPath,
                    childrenCount: layout.length
                  }}
                  onDrop={handleDrop}
                  path={currentPath}
                />
                {renderRow(row, currentPath)}
              </React.Fragment>
            );
          })}
          <DropZone
            data={{
              path: `${layout.length}`,
              childrenCount: layout.length
            }}
            onDrop={handleDrop}
            isLast
          />
        </div>

        <div className={styles.zoneArea}>
          <TrashDropZone
            data={{
              layout
            }}
            onDrop={handleDropToTrashBin}
          />
          <SaveZone
            data={{
              layout
            }}
          />
        </div>
      </div>
    </div>
  );
};
export default Example;
