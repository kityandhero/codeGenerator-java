(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[26],{deRo:function(e,t,a){"use strict";a.r(t);a("14J3");var n=a("BMrR"),r=(a("jCWc"),a("kPKH")),l=(a("IzEo"),a("bx4M")),c=(a("T2oS"),a("W9HT")),o=a("k1fw"),u=(a("miYZ"),a("tsqr")),i=a("fWQN"),s=a("mtLc"),p=a("Nsem"),m=a("oZsa"),h=a("yKVA"),d=a("q1tI"),g=a.n(d),f=a("/MKj"),b=a("9kvl"),y=a("Hx5s"),v=a("uJMD"),C=a("zvWe"),I=a("LLNw"),O=a("0Owb"),j=a("oBTY"),E=(a("lUTK"),a("BvKs")),M=a("7067"),k=a("6VBw"),w=function(e,t){return g.a.createElement(k["a"],Object.assign({},e,{ref:t,icon:M["a"]}))};w.displayName="BarsOutlined";var K,L,N,P=g.a.forwardRef(w),S=a("55Ip"),B=E["a"].SubMenu,D=function(e){function t(){var e,a;Object(i["a"])(this,t);for(var n=arguments.length,r=new Array(n),l=0;l<n;l++)r[l]=arguments[l];return a=Object(p["a"])(this,(e=Object(m["a"])(t)).call.apply(e,[this].concat(r))),a.getNavMenuItems=function(e,t){return e?e.filter((function(e){return e.name&&!e.hideInMenu})).map((function(e){return a.getSubMenuOrItem(e,t)})).filter((function(e){return e})):[]},a.getSelectedMenuKeys=function(){var e=[],t=a.props.currentCategoryId;return""!==(t||"")&&e.push(t),e},a.getSubMenuOrItem=function(e){if(e.children&&!e.hideChildrenInMenu&&e.children.some((function(e){return e.name}))){var t=e.name;return g.a.createElement(B,{title:e.icon?g.a.createElement("span",null,g.a.createElement(P,null),g.a.createElement("span",null,t)):t,key:e.helpCategoryId},a.getNavMenuItems(e.children))}return g.a.createElement(E["a"].Item,{key:e.helpCategoryId},a.getMenuItemPath(e))},a.getMenuItemPath=function(e){var t=e.name,n="/helpCenter/category/".concat(e.helpCategoryId),r=g.a.createElement(P,null),l=a.props,c=l.isMobile,o=l.onCollapse;return g.a.createElement(S["Link"],{to:n,replace:!0,onClick:c?function(){o(!0)}:void 0},r,g.a.createElement("span",null,t))},a}return Object(h["a"])(t,e),Object(s["a"])(t,[{key:"render",value:function(){var e=this.props,t=e.openKeys,a=e.theme,n=e.collapsed,r=this.getSelectedMenuKeys();!r.length&&t&&(r=[t[t.length-1]]);var l={};t&&!n&&(l={openKeys:0===t.length?Object(j["a"])(r):t});var c=this.props,o=c.handleOpenChange,u=c.menuData;return g.a.createElement(E["a"],Object(O["a"])({key:"2be2d3a3-308c-4e44-bfc4-56f86c2424bb",mode:"inline",theme:a,onOpenChange:o,selectedKeys:r},l),this.getNavMenuItems(u))}}]),t}(d["PureComponent"]),T=(K=Object(f["c"])((function(e){var t=e.helpCategory,a=e.global,n=e.loading;return{helpCategory:t,global:a,loading:n.models.helpCategory}})),K((N=function(e){function t(e){var a;return Object(i["a"])(this,t),a=Object(p["a"])(this,Object(m["a"])(t).call(this,e)),a.getApiData=function(e){var t=e.helpCategory.data;return t},a.afterLoadSuccess=function(e,t,n,r){var l=a.state.urlParams.helpCategoryId;if("no"===l)if(0===(t||[]).length)u["a"].error("\u6682\u65e0\u5e2e\u52a9\u4fe1\u606f\uff01");else{var c=t[0].helpCategoryId,o={pathname:"/helpCenter/category/".concat(c,"/pageList")};b["d"].replace(o)}},a.goToList=function(e){var t=a.state.pageNo,n=e.helpCategoryId,r={pathname:"/helpCenter/detail/load/".concat(n,"/").concat(t,"/basicInfo")};b["d"].push(r)},a.state=Object(o["a"])({},a.state,{},{pageName:"",loadApiPath:"helpCategory/list"}),a}return Object(h["a"])(t,e),Object(s["a"])(t,[{key:"render",value:function(){var e=this.props.children,t=this.state,a=t.urlParams.helpCategoryId,o=t.dataLoading,u=t.metaListData;return g.a.createElement(y["b"],null,g.a.createElement(n["a"],{gutter:24},g.a.createElement(r["a"],{lg:5,md:24},g.a.createElement(l["a"],{title:"\u5e2e\u52a9\u5bfc\u822a",bordered:!1,style:{marginBottom:24}},g.a.createElement(c["a"],{spinning:o},g.a.createElement(D,{currentCategoryId:a,menuData:u||[]})))),g.a.createElement(r["a"],{lg:19,md:24},e)))}}],[{key:"getDerivedStateFromProps",value:function(e,t){return Object(v["r"])(e,t,{category:""},I["a"])}}]),t}(C["a"]),L=N))||L);t["default"]=T}}]);