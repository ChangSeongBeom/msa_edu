import React, { useState, useEffect } from "react";
import { useRouter } from 'next/router';

function PortalDetail() {
  const router = useRouter();
  const [portalNm,setPortalNm]=useState();
  useEffect(() => {
    console.log("adsfdfsafdafa",router.query.Portal_Detail);
    setPortalNm(router.query.Portal_Detail);
  }, [router.query.Portal_Detail]);


  return (
    <div>
     {portalNm}
    </div>
  );
}

export default PortalDetail;