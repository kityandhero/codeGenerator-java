(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[18],{LJ4X:function(e,t,n){"use strict";n.r(t);var a,i,o,c=n("k1fw"),r=n("fWQN"),s=n("mtLc"),l=n("Nsem"),u=n("oZsa"),m=n("yKVA"),f=(n("lUTK"),n("BvKs")),d=n("q1tI"),g=n.n(d),h=n("/MKj"),p=n("9kvl"),v=n("Hx5s"),y=n("uJMD"),b=n("t6Xd"),w=n("bslF"),K=n.n(w),M=f["a"].Item,j=(a=Object(h["c"])((function(e){var t=e.customConfig,n=e.global,a=e.loading;return{customConfig:t,global:n,loading:a.models.customConfig}})),a((o=function(e){function t(e){var n;Object(r["a"])(this,t),n=Object(l["a"])(this,Object(u["a"])(t).call(this,e)),n.doDidMountTask=function(){window.addEventListener("resize",n.resize),n.resize()},n.beforeUnmount=function(){window.removeEventListener("resize",n.resize)},n.getMenu=function(){var e=n.state.menuMap;return Object.keys(e).map((function(t){return g.a.createElement(M,{key:t},e[t])}))},n.getRightTitle=function(){var e=n.state,t=e.selectKey,a=e.menuMap;return a[t]},n.selectKey=function(e){var t=e.key,a=n.state.menuCategory;p["d"].push("/customConfig/category/".concat(a[t])),n.setState({selectKey:t})},n.resize=function(){n.main&&requestAnimationFrame((function(){var e="inline";if(null!=n.main){var t=n.main.offsetWidth;null!=t&&(n.main.offsetWidth<641&&t>400&&(e="horizontal"),window.innerWidth<768&&t>400&&(e="horizontal"),n.setState({mode:e}))}}))};var a=e.match,i=e.location,o=e.global.customConfigCategoryList,s=a.params.category,m={},f={},d=null,h=null;Object(y["E"])(o)&&(o||[]).forEach((function(e,t){var n="c".concat(e.flag);m[n]=e.name,f[n]=e.flag,0===t&&(d=n,h=e.flag)}));var v=i.pathname.replace("".concat(a.path,"/"),""),b=Object(y["V"])(s)||"no"===s?h:s;return n.state=Object(c["a"])({},n.state,{},{loadDataAfterMount:!1,mode:"inline",menuMap:m,menuCategory:f,firstKey:d,firstCategory:h,currentCategory:b,selectKey:m[v]?v:d}),n}return Object(m["a"])(t,e),Object(s["a"])(t,[{key:"render",value:function(){var e=this,t=this.props.children,n=this.state,a=n.mode,i=n.selectKey;return g.a.createElement(v["b"],null,g.a.createElement("div",{className:K.a.main,ref:function(t){e.main=t}},g.a.createElement("div",{className:K.a.leftMenu},g.a.createElement(f["a"],{mode:a,selectedKeys:[i],onClick:this.selectKey},this.getMenu())),g.a.createElement("div",{className:K.a.right},g.a.createElement("div",{className:K.a.title},this.getRightTitle()),t)))}}]),t}(b["a"]),i=o))||i);t["default"]=j},bslF:function(e,t,n){e.exports={main:"antd-pro-pages-custom-config-index-main",leftMenu:"antd-pro-pages-custom-config-index-leftMenu",right:"antd-pro-pages-custom-config-index-right",title:"antd-pro-pages-custom-config-index-title"}}}]);