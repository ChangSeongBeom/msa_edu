

import React from 'react';
import Portal from "./Portal";
import { DndProvider } from "react-dnd";
import Backend from "react-dnd-html5-backend";
import { useRouter } from 'next/router';
import styles from "./index.module.css";
import PortalDetail from "./[Portal_Detail]"; 
function Index() {

  const router = useRouter();
  const isPortalDetailPage = router.asPath.startsWith('/portal/');

  console.log("testtest",isPortalDetailPage);
  return (
    <div className={styles.index}>
      {/* Render the Portal component without DndProvider */}
      <Portal />
      {/* Wrap PortalDetail with DndProvider */}
      {/* <DndProvider backend={Backend}>
        <PortalDetail />
      </DndProvider> */}
    </div>
  );
}

export default Index