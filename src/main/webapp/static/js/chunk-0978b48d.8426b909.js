(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0978b48d"],{"7f08":function(e,t,r){"use strict";function n(e){return e?new Date(e).toLocaleString():"无"}r.r(t),r.d(t,"filterTimeStamp",(function(){return n}))},c24f:function(e,t,r){"use strict";r.r(t),r.d(t,"getList",(function(){return a})),r.d(t,"getStoredValueLogs",(function(){return c})),r.d(t,"register",(function(){return u}));var n=r("b775");function a(){return Object(n["default"])({url:"/user/list",method:"get"})}function c(){return Object(n["default"])({url:"/storedvalue/log/list",method:"get"})}function u(e){return Object(n["default"])({url:"/user/register",method:"post",data:e})}},ce21:function(e,t,r){"use strict";r.r(t);var n=r("f2bf");function a(e,t,r,a,c,u){var i=Object(n["K"])("a-table");return Object(n["C"])(),Object(n["j"])(i,{columns:c.table.columns,"data-source":c.table.data,bordered:""},{time:Object(n["V"])((function(e){var t=e.text;return[Object(n["l"])(Object(n["N"])(u.filterTimeStamp(t)),1)]})),_:1},8,["columns","data-source"])}var c=r("c24f"),u=r("7f08"),i={mounted:function(){var e=this;Object(c["getStoredValueLogs"])().then((function(t){e.table.data=t.data}))},methods:{filterTimeStamp:u["filterTimeStamp"]},data:function(){return{table:{data:[],columns:[{title:"储值ID",dataIndex:"rechargeid",key:"rechargeid",sorter:function(e,t){return e.rechargeid-t.rechargeid},sortDirections:["descend","ascend"]},{title:"用户姓名",dataIndex:"username",key:"username"},{title:"储值金额",dataIndex:"recharge_amount",key:"recharge_amount",sorter:function(e,t){return e.recharge_amount-t.recharge_amount},sortDirections:["descend","ascend"]},{title:"储值时间",key:"recharge_time",dataIndex:"recharge_time",slots:{customRender:"time"},sorter:function(e,t){return e.recharge_time-t.recharge_time},sortDirections:["descend","ascend"]},{title:"储值类型",key:"type",dataIndex:"type"},{title:"备注",key:"remark",dataIndex:"remark"}]}}}};i.render=a;t["default"]=i}}]);