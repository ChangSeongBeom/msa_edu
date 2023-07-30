import React, { useState, useCallback, useEffect } from "react";
import axios from "axios";
import useUser from '@hooks/useUser'
import { common, statisticsService } from '@service';
import PortalDesc from './PortalDesc';
import styles from './css/portal.module.css';


function Portal() {
  
    const { user, loading, isLogin, loggedOut } = useUser()
    const [portalList,setPortalList]=useState([]);

    useEffect(() => {
    if (isLogin) {
      axios.get(`/portal-service/api/v1/getPortals/portalDesc`, {
        headers: common.headers,
      })
        .then(function (response) {
          console.log("portal desc",response.data);
          setPortalList(response.data);

        })
        .catch(error => {
          console.error('Error fetching data:', error);
        });
    }
  }, [isLogin]);


  return (
    <div className={styles.portal}>
      <div className={styles.portalBanner}></div>
      <div className={styles.portalDesc}>
          {
              portalList.map((portal,idx)=>
                  <PortalDesc portalList={portalList} idx={idx}/>
              )
          }
      
      </div>
    </div>
  
  )
}

export default Portal





