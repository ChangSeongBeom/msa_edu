import React, { useState, useCallback, useEffect } from "react";

import styles from "./index.module.css";
import useUser from '@hooks/useUser';
import axios from "axios";
import Cookies from 'cookies';
import { ACCESS_TOKEN, CLAIM_NAME, SERVER_API_URL } from '@constants/env'
import { common, statisticsService } from '@service';


const SaveZone = ({ data, onClick }) => {
  const [saveIsActive, setSaveIsActive] = useState(false);

 
  function dataSend(data){
    for (const key in data) {
      if (Object.hasOwnProperty.call(data, key)) {
        const item = data[key];
        console.log("item",item);

       
        axios.post('/portal-service/api/v1/getPortals/save', item
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