import React, { useEffect, useState } from "react";
import ReactPlayer from "react-player";
import axios from "axios";
import useUser from '@hooks/useUser'
import { common } from '@service';

function Contentplay({contentNo}) {
  const { isLogin } = useUser()
  const [url,setUrl]=useState();
  const [isLoading, setIsLoading] = useState(true); // Add loading state
  const [error, setError] = useState(null); // Add error state
  console.log(contentNo);

  useEffect(() => {
    if (isLogin ) {
      axios.get(`/portal-service/api/v1/contents/${contentNo}`, {
        headers: common.headers,
      })
        .then(function (response) {
          setUrl(response.data.contentUrl);
          setIsLoading(false); // Set isLoading to false once data is fetched
          console.log("콘텐츠 결과", response.data.contentUrl);

        })
        .catch(error => {
          setIsLoading(false); // Set isLoading to false in case of error
          setError(error); // Set the error state
          console.error('Error fetching data:', error);
        });
    }
  }, [isLogin, contentNo]);
  
    return (
      
      <div>
        {contentNo}
        <section>
          <h2>React Player</h2>
       
          {url && ( 
            <div>
              <ReactPlayer url={url} controls={true} />
            </div>
        )}
        
        </section>
      </div>
    )
  
}

export default Contentplay
