import shortid from "shortid";

export const SIDEBAR_ITEM = "sidebarItem";
export const ROW = "row";
export const COLUMN = "column";
export const COMPONENT = "component";

export const SIDEBAR_ITEMS = [
  {
    id: 0,
    type: SIDEBAR_ITEM,
    component: {
      type: "수강신청",
      content: "수강신청 영역"
    }
  },
  {
    id: 1,
    type: SIDEBAR_ITEM,
    component: {
      type: "정규학습",
      content: "정규학습 영역"
    }
  },
  {
    id: 2,
    type: SIDEBAR_ITEM,
    component: {
      type: "상시학습",
      content: "상시학습 영역"
    }
  },
  {
    id: 3,
    type: SIDEBAR_ITEM,
    component: {
      type: "커뮤니티",
      content: "커뮤니티 영역"
    }
  },
  {
    id: 4,
    type: SIDEBAR_ITEM,
    component: {
      type: "인증",
      content: "인증 영역"
    }
  },
  {
    id: 5,
    type: SIDEBAR_ITEM,
    component: {
      type: "CDP/IDP",
      content: "CDP/IDP 영역"
    }
  },
  {
    id: 6,
    type: SIDEBAR_ITEM,
    component: {
      type: "관리자추천",
      content: "관리자추천 영역"
    }
  },
  {
    id: 7,
    type: SIDEBAR_ITEM,
    component: {
      type: "쇼츠영상",
      content: "쇼츠영상 영역"
    }
  },
  {
    id: 8,
    type: SIDEBAR_ITEM,
    component: {
      type: "게시판",
      content: "게시판 영역"
    }
  }
  
];
