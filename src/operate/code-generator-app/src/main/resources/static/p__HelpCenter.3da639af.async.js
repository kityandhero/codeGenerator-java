(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[28],{deRo:function(e,t,a){"use strict";a.r(t);a("EVT3");var n=a("jKSN"),r=(a("zx9L"),a("owjU")),c=(a("Lfc8"),a("Ju1I")),l=(a("xaDS"),a("LVIC")),o=a("xFFA"),u=(a("spZx"),a("rgyI")),i=a("QyQr"),s=a("G2pS"),p=a("pUne"),m=a("euny"),h=a("ZZRV"),g=a.n(h),d=a("9kvl"),f=a("t+9W"),v=a("uJMD"),y=a("zvWe"),b=a("LLNw"),C=a("YOqz"),I=a("Lvms"),O=(a("ASDf"),a("g9Mj")),j={name:"bars",theme:"outlined",icon:{tag:"svg",attrs:{viewBox:"0 0 1024 1024",focusable:"false"},children:[{tag:"path",attrs:{d:"M912 192H328c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h584c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zm0 284H328c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h584c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zm0 284H328c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h584c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zM104 228a56 56 0 10112 0 56 56 0 10-112 0zm0 284a56 56 0 10112 0 56 56 0 10-112 0zm0 284a56 56 0 10112 0 56 56 0 10-112 0z"}}]}},E=j,M=a("340B"),w=function(e,t){return h["createElement"](M["a"],Object.assign({},e,{ref:t,icon:E}))};w.displayName="BarsOutlined";var L,S,k,z=h["forwardRef"](w),D=a("FR5j"),N=O["a"].SubMenu,P=function(e){Object(p["a"])(a,e);var t=Object(m["a"])(a);function a(){var e;Object(i["a"])(this,a);for(var n=arguments.length,r=new Array(n),c=0;c<n;c++)r[c]=arguments[c];return e=t.call.apply(t,[this].concat(r)),e.getNavMenuItems=function(t,a){return t?t.filter((function(e){return e.name&&!e.hideInMenu})).map((function(t){return e.getSubMenuOrItem(t,a)})).filter((function(e){return e})):[]},e.getSelectedMenuKeys=function(){var t=[],a=e.props.currentCategoryId;return""!==(a||"")&&t.push(a),t},e.getSubMenuOrItem=function(t){if(t.children&&!t.hideChildrenInMenu&&t.children.some((function(e){return e.name}))){var a=t.name;return g.a.createElement(N,{title:t.icon?g.a.createElement("span",null,g.a.createElement(z,null),g.a.createElement("span",null,a)):a,key:t.helpCategoryId},e.getNavMenuItems(t.children))}return g.a.createElement(O["a"].Item,{key:t.helpCategoryId},e.getMenuItemPath(t))},e.getMenuItemPath=function(t){var a=t.name,n="/helpCenter/category/".concat(t.helpCategoryId),r=g.a.createElement(z,null),c=e.props,l=c.isMobile,o=c.onCollapse;return g.a.createElement(D["a"],{to:n,replace:!0,onClick:l?function(){o(!0)}:void 0},r,g.a.createElement("span",null,a))},e}return Object(s["a"])(a,[{key:"render",value:function(){var e=this.props,t=e.openKeys,a=e.theme,n=e.collapsed,r=this.getSelectedMenuKeys();!r.length&&t&&(r=[t[t.length-1]]);var c={};t&&!n&&(c={openKeys:0===t.length?Object(I["a"])(r):t});var l=this.props,o=l.handleOpenChange,u=l.menuData;return g.a.createElement(O["a"],Object(C["a"])({key:"2be2d3a3-308c-4e44-bfc4-56f86c2424bb",mode:"inline",theme:a,onOpenChange:o,selectedKeys:r},c),this.getNavMenuItems(u))}}]),a}(h["PureComponent"]),K=(L=Object(d["a"])((function(e){var t=e.helpCategory,a=e.global,n=e.loading;return{helpCategory:t,global:a,loading:n.models.helpCategory}})),L((k=function(e){Object(p["a"])(a,e);var t=Object(m["a"])(a);function a(e){var n;return Object(i["a"])(this,a),n=t.call(this,e),n.getApiData=function(e){var t=e.helpCategory.data;return t},n.afterLoadSuccess=function(e,t,a,r){var c=n.state.urlParams.helpCategoryId;if("no"===c)if(0===(t||[]).length)u["a"].error("\u6682\u65e0\u5e2e\u52a9\u4fe1\u606f\uff01");else{var l=t[0].helpCategoryId,o={pathname:"/helpCenter/category/".concat(l,"/pageList")};d["d"].replace(o)}},n.goToList=function(e){var t=n.state.pageNo,a=e.helpCategoryId,r={pathname:"/helpCenter/detail/load/".concat(a,"/").concat(t,"/basicInfo")};d["d"].push(r)},n.state=Object(o["a"])({},n.state,{},{pageName:"",loadApiPath:"helpCategory/list"}),n}return Object(s["a"])(a,[{key:"render",value:function(){var e=this.props.children,t=this.state,a=t.urlParams.helpCategoryId,o=t.dataLoading,u=t.metaListData;return g.a.createElement(f["b"],null,g.a.createElement(n["a"],{gutter:24},g.a.createElement(r["a"],{lg:5,md:24},g.a.createElement(c["a"],{title:"\u5e2e\u52a9\u5bfc\u822a",bordered:!1,style:{marginBottom:24}},g.a.createElement(l["a"],{spinning:o},g.a.createElement(P,{currentCategoryId:a,menuData:u||[]})))),g.a.createElement(r["a"],{lg:19,md:24},e)))}}],[{key:"getDerivedStateFromProps",value:function(e,t){return Object(v["r"])(e,t,{category:""},b["a"])}}]),a}(y["a"]),S=k))||S);t["default"]=K}}]);