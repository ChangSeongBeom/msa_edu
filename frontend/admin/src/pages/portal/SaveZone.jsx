import React, { useState } from "react";
import styles from "./index.module.css";

const SaveZone = ({ data, onClick }) => {
  const [saveIsActive, setSaveIsActive] = useState(false);

  return (
    <button
      className={`${styles.saveZone} ${saveIsActive ? styles.active : ""}`}
      onClick={() => {
        setSaveIsActive(!saveIsActive);
        alert("저장되었습니다");
        console.log(data);
        //onClick(data); // Pass the data to the onClick handler
      }}
    >
      저장하기
    </button>
  );
};

export default SaveZone;