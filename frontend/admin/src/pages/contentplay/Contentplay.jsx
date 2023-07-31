import React, { useEffect, useState } from "react";
import ReactPlayer from "react-player";
import axios from "axios";
import useUser from '@hooks/useUser'
import { common } from '@service';

function Contentplay({ contentNo }) {
  console.log("contentNo",contentNo);
  const { isLogin } = useUser()
  const [url, setUrl] = useState(null);

  const [error, setError] = useState(null);

  useEffect(() => {
    if (isLogin) {
      axios
        .get(`/portal-service/api/v1/contents/${contentNo}`, {
          headers: common.headers,
        })
        .then(function (response) {
          setUrl(response.data.contentUrl);
    
          console.log("콘텐츠 결과", response.data.contentUrl);
        })
        .catch(error => {
          setError(error.message);
       
          console.error('Error fetching data:', error);
        });
    }
  }, [contentNo, isLogin]);

  if (!contentNo) {
    return <div>No contentNo provided</div>;
  }



  return (
    <div>
      {contentNo}
      <section>
        <h2>React Player</h2>
      
          <div>
            <ReactPlayer url={url} controls={true} />
          </div>
    
      
      </section>
    </div>
  );
}

export default Contentplay;