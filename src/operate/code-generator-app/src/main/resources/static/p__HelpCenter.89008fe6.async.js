(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[28],{deRo:function(e,t,a){"use strict";a.r(t);a("14J3");var n=a("BMrR"),r=(a("jCWc"),a("kPKH")),c=(a("IzEo"),a("bx4M")),l=(a("T2oS"),a("W9HT")),o=a("VTBJ"),u=(a("miYZ"),a("tsqr")),i=a("1OyB"),s=a("vuIU"),p=a("Ji7U"),m=a("LK+K"),h=a("q1tI"),d=a.n(h),g=a("9kvl"),v=a("Hx5s"),f=a("uJMD"),y=a("zvWe"),b=a("LLNw"),C=a("wx14"),I=a("KQm4"),O=(a("lUTK"),a("BvKs")),E={name:"bars",theme:"outlined",icon:{tag:"svg",attrs:{viewBox:"0 0 1024 1024",focusable:"false"},children:[{tag:"path",attrs:{d:"M912 192H328c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h584c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zm0 284H328c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h584c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zm0 284H328c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h584c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zM104 228a56 56 0 10112 0 56 56 0 10-112 0zm0 284a56 56 0 10112 0 56 56 0 10-112 0zm0 284a56 56 0 10112 0 56 56 0 10-112 0z"}}]}},M=E,j=a("6VBw"),w=function(e,t){return h["createElement"](j["a"],Object.assign({},e,{ref:t,icon:M}))};w.displayName="BarsOutlined";var k,K,z,B=h["forwardRef"](w),L=a("55Ip"),P=O["a"].SubMenu,S=function(e){Object(p["a"])(a,e);var t=Object(m["a"])(a);function a(){var e;Object(i["a"])(this,a);for(var n=arguments.length,r=new Array(n),c=0;c<n;c++)r[c]=arguments[c];return e=t.call.apply(t,[this].concat(r)),e.getNavMenuItems=function(t,a){return t?t.filter((function(e){return e.name&&!e.hideInMenu})).map((function(t){return e.getSubMenuOrItem(t,a)})).filter((function(e){return e})):[]},e.getSelectedMenuKeys=function(){var t=[],a=e.props.currentCategoryId;return""!==(a||"")&&t.push(a),t},e.getSubMenuOrItem=function(t){if(t.children&&!t.hideChildrenInMenu&&t.children.some((function(e){return e.name}))){var a=t.name;return d.a.createElement(P,{title:t.icon?d.a.createElement("span",null,d.a.createElement(B,null),d.a.createElement("span",null,a)):a,key:t.helpCategoryId},e.getNavMenuItems(t.children))}return d.a.createElement(O["a"].Item,{key:t.helpCategoryId},e.getMenuItemPath(t))},e.getMenuItemPath=function(t){var a=t.name,n="/helpCenter/category/".concat(t.helpCategoryId),r=d.a.createElement(B,null),c=e.props,l=c.isMobile,o=c.onCollapse;return d.a.createElement(L["a"],{to:n,replace:!0,onClick:l?function(){o(!0)}:void 0},r,d.a.createElement("span",null,a))},e}return Object(s["a"])(a,[{key:"render",value:function(){var e=this.props,t=e.openKeys,a=e.theme,n=e.collapsed,r=this.getSelectedMenuKeys();!r.length&&t&&(r=[t[t.length-1]]);var c={};t&&!n&&(c={openKeys:0===t.length?Object(I["a"])(r):t});var l=this.props,o=l.handleOpenChange,u=l.menuData;return d.a.createElement(O["a"],Object(C["a"])({key:"2be2d3a3-308c-4e44-bfc4-56f86c2424bb",mode:"inline",theme:a,onOpenChange:o,selectedKeys:r},c),this.getNavMenuItems(u))}}]),a}(h["PureComponent"]),N=(k=Object(g["a"])((function(e){var t=e.helpCategory,a=e.global,n=e.loading;return{helpCategory:t,global:a,loading:n.models.helpCategory}})),k((z=function(e){Object(p["a"])(a,e);var t=Object(m["a"])(a);function a(e){var n;return Object(i["a"])(this,a),n=t.call(this,e),n.getApiData=function(e){var t=e.helpCategory.data;return t},n.afterLoadSuccess=function(e,t,a,r){var c=n.state.urlParams.helpCategoryId;if("no"===c)if(0===(t||[]).length)u["a"].error("\u6682\u65e0\u5e2e\u52a9\u4fe1\u606f\uff01");else{var l=t[0].helpCategoryId,o={pathname:"/helpCenter/category/".concat(l,"/pageList")};g["d"].replace(o)}},n.goToList=function(e){var t=n.state.pageNo,a=e.helpCategoryId,r={pathname:"/helpCenter/detail/load/".concat(a,"/").concat(t,"/basicInfo")};g["d"].push(r)},n.state=Object(o["a"])({},n.state,{},{pageName:"",loadApiPath:"helpCategory/list"}),n}return Object(s["a"])(a,[{key:"render",value:function(){var e=this.props.children,t=this.state,a=t.urlParams.helpCategoryId,o=t.dataLoading,u=t.metaListData;return d.a.createElement(v["b"],null,d.a.createElement(n["a"],{gutter:24},d.a.createElement(r["a"],{lg:5,md:24},d.a.createElement(c["a"],{title:"\u5e2e\u52a9\u5bfc\u822a",bordered:!1,style:{marginBottom:24}},d.a.createElement(l["a"],{spinning:o},d.a.createElement(S,{currentCategoryId:a,menuData:u||[]})))),d.a.createElement(r["a"],{lg:19,md:24},e)))}}],[{key:"getDerivedStateFromProps",value:function(e,t){return Object(f["r"])(e,t,{category:""},b["a"])}}]),a}(y["a"]),K=z))||K);t["default"]=N}}]);