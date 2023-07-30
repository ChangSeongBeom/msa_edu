import React, { useState, useCallback, useEffect } from "react";

import styles from "./index.module.css";
import useUser from '@hooks/useUser';
import axios from "axios";
import Cookies from 'cookies';
import { ACCESS_TOKEN, CLAIM_NAME, SERVER_API_URL } from '@constants/env'
import { common, statisticsService } from '@service';


const SaveZone = ({ data,portalNm,onClick }) => {
  const [saveIsActive, setSaveIsActive] = useState(false);
  //const [newportalNm,setPortalNm]=useState(portalNm);



  function dataSend(data){
    for (const key in data) {
      if (Object.hasOwnProperty.call(data, key)) {
        const item = data[key];
        //const portalNmString = JSON.stringify(portalNm);
        const portalNmString = portalNm.portalNm;
    

        axios.post(`/portal-service/api/v1/getPortals/save/${portalNmString}`, item
        //axios.post(`/portal-service/api/v1/getPortals/save/${portalNmString}`, data
        ,{
          headers: common.headers
        }
        )
        .then(response => {
          console.log('Data saved successfully:', response.data);
          // Do something with the response if needed
        })
        .catch(error => {
          console.error('Error saving data:', error);
          // Handle error if needed
        });
     

      }
    }
  }
  return (
    <button
      className={`${styles.saveZone} ${saveIsActive ? styles.active : ""}`}
      onClick={() => {
        setSaveIsActive(!saveIsActive);
        dataSend(data);
        //onClick(data); // Pass the data to the onClick handler
      }}
    >
      저장하기
    </button>
  );
};

export default SaveZone;