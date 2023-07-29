import React from 'react'
import styles from './css/portal.module.css';
//import {useNavigate} from 'react-router-dom';
import { useRouter } from 'next/router'
function PortalDesc({portalList,idx}) {
    console.log(portalList,idx);
    const router = useRouter();


    const handlePortalClick = () => {
        const portal_nm = portalList[idx].portal_nm;
        console.log("portal_nm",portal_nm);
   
        router.push(`/portal/${(portal_nm)}`);
      };
  return (

    <div className={styles.portalDetail}>
      <div className={styles.portalDetailDesc} onClick={handlePortalClick}>{portalList[idx].portal_desc}</div>
      <div className={styles.portalDetailButton}>확인하기</div>
    </div>
  )
}

export default PortalDesc
