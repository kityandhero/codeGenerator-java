(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[2],{bmMU:function(e,t){var r="undefined"!==typeof Element,n="function"===typeof Map,a="function"===typeof Set,o="function"===typeof ArrayBuffer&&!!ArrayBuffer.isView;function i(e,t){if(e===t)return!0;if(e&&t&&"object"==typeof e&&"object"==typeof t){if(e.constructor!==t.constructor)return!1;var s,c,u,l;if(Array.isArray(e)){if(s=e.length,s!=t.length)return!1;for(c=s;0!==c--;)if(!i(e[c],t[c]))return!1;return!0}if(n&&e instanceof Map&&t instanceof Map){if(e.size!==t.size)return!1;l=e.entries();while(!(c=l.next()).done)if(!t.has(c.value[0]))return!1;l=e.entries();while(!(c=l.next()).done)if(!i(c.value[1],t.get(c.value[0])))return!1;return!0}if(a&&e instanceof Set&&t instanceof Set){if(e.size!==t.size)return!1;l=e.entries();while(!(c=l.next()).done)if(!t.has(c.value[0]))return!1;return!0}if(o&&ArrayBuffer.isView(e)&&ArrayBuffer.isView(t)){if(s=e.length,s!=t.length)return!1;for(c=s;0!==c--;)if(e[c]!==t[c])return!1;return!0}if(e.constructor===RegExp)return e.source===t.source&&e.flags===t.flags;if(e.valueOf!==Object.prototype.valueOf)return e.valueOf()===t.valueOf();if(e.toString!==Object.prototype.toString)return e.toString()===t.toString();if(u=Object.keys(e),s=u.length,s!==Object.keys(t).length)return!1;for(c=s;0!==c--;)if(!Object.prototype.hasOwnProperty.call(t,u[c]))return!1;if(r&&e instanceof Element)return!1;for(c=s;0!==c--;)if(("_owner"!==u[c]||!e.$$typeof)&&!i(e[u[c]],t[u[c]]))return!1;return!0}return e!==e&&t!==t}e.exports=function(e,t){try{return i(e,t)}catch(r){if((r.message||"").match(/stack|recursion/i))return console.warn("react-fast-compare cannot handle circular refs"),!1;throw r}}},"nsf+":function(e,t,r){"use strict";r.r(t);var n=r("k1fw"),a=r("q1tI"),o=r.n(a),i=r("uYtH"),s=r("9kvl"),c=r("bmMU"),u=r.n(c),l=r("QLaP"),p=r.n(l),f=r("17x9"),d=r.n(f),h=r("Gytx"),y=r.n(h),m={BASE:"base",BODY:"body",HEAD:"head",HTML:"html",LINK:"link",META:"meta",NOSCRIPT:"noscript",SCRIPT:"script",STYLE:"style",TITLE:"title",FRAGMENT:"Symbol(react.fragment)"},b=Object.keys(m).map((function(e){return m[e]})),g={accesskey:"accessKey",charset:"charSet",class:"className",contenteditable:"contentEditable",contextmenu:"contextMenu","http-equiv":"httpEquiv",itemprop:"itemProp",tabindex:"tabIndex"},T=Object.keys(g).reduce((function(e,t){return e[g[t]]=t,e}),{}),v=function(e,t){for(var r=e.length-1;r>=0;r-=1){var n=e[r];if(Object.prototype.hasOwnProperty.call(n,t))return n[t]}return null},O=function(e){var t=v(e,m.TITLE),r=v(e,"titleTemplate");if(Array.isArray(t)&&(t=t.join("")),r&&t)return r.replace(/%s/g,(function(){return t}));var n=v(e,"defaultTitle");return t||n||void 0},A=function(e){return v(e,"onChangeClientState")||function(){}},C=function(e,t){return t.filter((function(t){return void 0!==t[e]})).map((function(t){return t[e]})).reduce((function(e,t){return Object.assign({},e,t)}),{})},j=function(e,t){return t.filter((function(e){return void 0!==e[m.BASE]})).map((function(e){return e[m.BASE]})).reverse().reduce((function(t,r){if(!t.length)for(var n=Object.keys(r),a=0;a<n.length;a+=1){var o=n[a].toLowerCase();if(-1!==e.indexOf(o)&&r[o])return t.concat(r)}return t}),[])},E=function(e,t,r){var n={};return r.filter((function(t){return!!Array.isArray(t[e])||(void 0!==t[e]&&console&&"function"==typeof console.warn&&console.warn("Helmet: "+e+' should be of type "Array". Instead found type "'+typeof t[e]+'"'),!1)})).map((function(t){return t[e]})).reverse().reduce((function(e,r){var a={};r.filter((function(e){for(var r,o=Object.keys(e),i=0;i<o.length;i+=1){var s=o[i],c=s.toLowerCase();-1===t.indexOf(c)||"rel"===r&&"canonical"===e[r].toLowerCase()||"rel"===c&&"stylesheet"===e[c].toLowerCase()||(r=c),-1===t.indexOf(s)||"innerHTML"!==s&&"cssText"!==s&&"itemprop"!==s||(r=s)}if(!r||!e[r])return!1;var u=e[r].toLowerCase();return n[r]||(n[r]={}),a[r]||(a[r]={}),!n[r][u]&&(a[r][u]=!0,!0)})).reverse().forEach((function(t){return e.push(t)}));for(var o=Object.keys(a),i=0;i<o.length;i+=1){var s=o[i],c=Object.assign({},n[s],a[s]);n[s]=c}return e}),[]).reverse()},S=function(e){return Array.isArray(e)?e.join(""):e},w=[m.NOSCRIPT,m.SCRIPT,m.STYLE],x=function(e,t){return void 0===t&&(t=!0),!1===t?String(e):String(e).replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/"/g,"&quot;").replace(/'/g,"&#x27;")},I=function(e){return Object.keys(e).reduce((function(t,r){var n=void 0!==e[r]?r+'="'+e[r]+'"':""+r;return t?t+" "+n:n}),"")},L=function(e,t){return void 0===t&&(t={}),Object.keys(e).reduce((function(t,r){return t[g[r]||r]=e[r],t}),t)},P=function(e,t,r){switch(e){case m.TITLE:return{toComponent:function(){return r=L(t.titleAttributes,{key:e=t.title,"data-rh":!0}),[o.a.createElement(m.TITLE,r,e)];var e,r},toString:function(){return function(e,r,n,a){var o=I(t.titleAttributes),i=S(r);return o?"<"+e+' data-rh="true" '+o+">"+x(i,a)+"</"+e+">":"<"+e+' data-rh="true">'+x(i,a)+"</"+e+">"}(e,t.title,0,r)}};case"bodyAttributes":case"htmlAttributes":return{toComponent:function(){return L(t)},toString:function(){return I(t)}};default:return{toComponent:function(){return function(e,t){return t.map((function(t,r){var n={key:r,"data-rh":!0};return Object.keys(t).forEach((function(e){var r=g[e]||e;"innerHTML"===r||"cssText"===r?n.dangerouslySetInnerHTML={__html:t.innerHTML||t.cssText}:n[r]=t[e]})),o.a.createElement(e,n)}))}(e,t)},toString:function(){return function(e,t,r){return t.reduce((function(t,n){var a=Object.keys(n).filter((function(e){return!("innerHTML"===e||"cssText"===e)})).reduce((function(e,t){var a=void 0===n[t]?t:t+'="'+x(n[t],r)+'"';return e?e+" "+a:a}),""),o=n.innerHTML||n.cssText||"",i=-1===w.indexOf(e);return t+"<"+e+' data-rh="true" '+a+(i?"/>":">"+o+"</"+e+">")}),"")}(e,t,r)}}}},k=function(e){var t=e.bodyAttributes,r=e.encode,n=e.htmlAttributes,a=e.linkTags,o=e.metaTags,i=e.noscriptTags,s=e.scriptTags,c=e.styleTags,u=e.title;void 0===u&&(u="");var l=e.titleAttributes;return{base:P(m.BASE,e.baseTag,r),bodyAttributes:P("bodyAttributes",t,r),htmlAttributes:P("htmlAttributes",n,r),link:P(m.LINK,a,r),meta:P(m.META,o,r),noscript:P(m.NOSCRIPT,i,r),script:P(m.SCRIPT,s,r),style:P(m.STYLE,c,r),title:P(m.TITLE,{title:u,titleAttributes:l},r)}},M=o.a.createContext({}),N=d.a.shape({setHelmet:d.a.func,helmetInstances:d.a.shape({get:d.a.func,add:d.a.func,remove:d.a.func})}),H="undefined"!=typeof document,R=function(e){function t(r){var n=this;e.call(this,r),this.instances=[],this.value={setHelmet:function(e){n.props.context.helmet=e},helmetInstances:{get:function(){return n.instances},add:function(e){n.instances.push(e)},remove:function(e){var t=n.instances.indexOf(e);n.instances.splice(t,1)}}},t.canUseDOM||(r.context.helmet=k({baseTag:[],bodyAttributes:{},encodeSpecialCharacters:!0,htmlAttributes:{},linkTags:[],metaTags:[],noscriptTags:[],scriptTags:[],styleTags:[],title:"",titleAttributes:{}}))}return e&&(t.__proto__=e),(t.prototype=Object.create(e&&e.prototype)).constructor=t,t.prototype.render=function(){return o.a.createElement(M.Provider,{value:this.value},this.props.children)},t}(a["Component"]);R.canUseDOM=H,R.propTypes={context:d.a.shape({helmet:d.a.shape()}),children:d.a.node.isRequired},R.defaultProps={context:{}},R.displayName="HelmetProvider";var _=function(e,t){var r,n=document.head||document.querySelector(m.HEAD),a=n.querySelectorAll(e+"[data-rh]"),o=[].slice.call(a),i=[];return t&&t.length&&t.forEach((function(t){var n=document.createElement(e);for(var a in t)Object.prototype.hasOwnProperty.call(t,a)&&("innerHTML"===a?n.innerHTML=t.innerHTML:"cssText"===a?n.styleSheet?n.styleSheet.cssText=t.cssText:n.appendChild(document.createTextNode(t.cssText)):n.setAttribute(a,void 0===t[a]?"":t[a]));n.setAttribute("data-rh","true"),o.some((function(e,t){return r=t,n.isEqualNode(e)}))?o.splice(r,1):i.push(n)})),o.forEach((function(e){return e.parentNode.removeChild(e)})),i.forEach((function(e){return n.appendChild(e)})),{oldTags:o,newTags:i}},B=function(e,t){var r=document.getElementsByTagName(e)[0];if(r){for(var n=r.getAttribute("data-rh"),a=n?n.split(","):[],o=[].concat(a),i=Object.keys(t),s=0;s<i.length;s+=1){var c=i[s],u=t[c]||"";r.getAttribute(c)!==u&&r.setAttribute(c,u),-1===a.indexOf(c)&&a.push(c);var l=o.indexOf(c);-1!==l&&o.splice(l,1)}for(var p=o.length-1;p>=0;p-=1)r.removeAttribute(o[p]);a.length===o.length?r.removeAttribute("data-rh"):r.getAttribute("data-rh")!==i.join(",")&&r.setAttribute("data-rh",i.join(","))}},D=function(e,t){var r=e.baseTag,n=e.htmlAttributes,a=e.linkTags,o=e.metaTags,i=e.noscriptTags,s=e.onChangeClientState,c=e.scriptTags,u=e.styleTags,l=e.title,p=e.titleAttributes;B(m.BODY,e.bodyAttributes),B(m.HTML,n),function(e,t){void 0!==e&&document.title!==e&&(document.title=S(e)),B(m.TITLE,t)}(l,p);var f={baseTag:_(m.BASE,r),linkTags:_(m.LINK,a),metaTags:_(m.META,o),noscriptTags:_(m.NOSCRIPT,i),scriptTags:_(m.SCRIPT,c),styleTags:_(m.STYLE,u)},d={},h={};Object.keys(f).forEach((function(e){var t=f[e],r=t.newTags,n=t.oldTags;r.length&&(d[e]=r),n.length&&(h[e]=f[e].oldTags)})),t&&t(),s(e,d,h)},Y=null,q=function(e){function t(){for(var t=[],r=arguments.length;r--;)t[r]=arguments[r];e.apply(this,t),this.rendered=!1}return e&&(t.__proto__=e),(t.prototype=Object.create(e&&e.prototype)).constructor=t,t.prototype.shouldComponentUpdate=function(e){return!y()(e,this.props)},t.prototype.componentDidUpdate=function(){this.emitChange()},t.prototype.componentWillUnmount=function(){this.props.context.helmetInstances.remove(this),this.emitChange()},t.prototype.emitChange=function(){var e,t,r=this.props.context,n=r.setHelmet,a=null,o=(e=r.helmetInstances.get().map((function(e){var t=Object.assign({},e.props);return delete t.context,t})),{baseTag:j(["href"],e),bodyAttributes:C("bodyAttributes",e),defer:v(e,"defer"),encode:v(e,"encodeSpecialCharacters"),htmlAttributes:C("htmlAttributes",e),linkTags:E(m.LINK,["rel","href"],e),metaTags:E(m.META,["name","charset","http-equiv","property","itemprop"],e),noscriptTags:E(m.NOSCRIPT,["innerHTML"],e),onChangeClientState:A(e),scriptTags:E(m.SCRIPT,["src","innerHTML"],e),styleTags:E(m.STYLE,["cssText"],e),title:O(e),titleAttributes:C("titleAttributes",e)});R.canUseDOM?(t=o,Y&&cancelAnimationFrame(Y),t.defer?Y=requestAnimationFrame((function(){D(t,(function(){Y=null}))})):(D(t),Y=null)):k&&(a=k(o)),n(a)},t.prototype.init=function(){this.rendered||(this.rendered=!0,this.props.context.helmetInstances.add(this),this.emitChange())},t.prototype.render=function(){return this.init(),null},t}(a["Component"]);function U(e,t){var r={};for(var n in e)Object.prototype.hasOwnProperty.call(e,n)&&-1===t.indexOf(n)&&(r[n]=e[n]);return r}q.propTypes={context:N.isRequired},q.displayName="HelmetDispatcher";var K=function(e){function t(){e.apply(this,arguments)}return e&&(t.__proto__=e),(t.prototype=Object.create(e&&e.prototype)).constructor=t,t.prototype.shouldComponentUpdate=function(e){return!u()(this.props,e)},t.prototype.mapNestedChildrenToProps=function(e,t){if(!t)return null;switch(e.type){case m.SCRIPT:case m.NOSCRIPT:return{innerHTML:t};case m.STYLE:return{cssText:t};default:throw new Error("<"+e.type+" /> elements are self-closing and can not contain children. Refer to our API for more information.")}},t.prototype.flattenArrayTypeChildren=function(e){var t,r=e.child,n=e.arrayTypeChildren;return Object.assign({},n,((t={})[r.type]=(n[r.type]||[]).concat([Object.assign({},e.newChildProps,this.mapNestedChildrenToProps(r,e.nestedChildren))]),t))},t.prototype.mapObjectTypeChildren=function(e){var t,r,n=e.child,a=e.newProps,o=e.newChildProps,i=e.nestedChildren;switch(n.type){case m.TITLE:return Object.assign({},a,((t={})[n.type]=i,t),{titleAttributes:Object.assign({},o)});case m.BODY:return Object.assign({},a,{bodyAttributes:Object.assign({},o)});case m.HTML:return Object.assign({},a,{htmlAttributes:Object.assign({},o)});default:return Object.assign({},a,((r={})[n.type]=Object.assign({},o),r))}},t.prototype.mapArrayTypeChildrenToProps=function(e,t){var r=Object.assign({},t);return Object.keys(e).forEach((function(t){var n;r=Object.assign({},r,((n={})[t]=e[t],n))})),r},t.prototype.warnOnInvalidChildren=function(e,t){return p()(b.some((function(t){return e.type===t})),"function"==typeof e.type?"You may be attempting to nest <Helmet> components within each other, which is not allowed. Refer to our API for more information.":"Only elements types "+b.join(", ")+" are allowed. Helmet does not support rendering <"+e.type+"> elements. Refer to our API for more information."),p()(!t||"string"==typeof t||Array.isArray(t)&&!t.some((function(e){return"string"!=typeof e})),"Helmet expects a string as a child of <"+e.type+">. Did you forget to wrap your children in braces? ( <"+e.type+">{``}</"+e.type+"> ) Refer to our API for more information."),!0},t.prototype.mapChildrenToProps=function(e,t){var r=this,n={};return o.a.Children.forEach(e,(function(e){if(e&&e.props){var a=e.props,o=a.children,i=U(a,["children"]),s=Object.keys(i).reduce((function(e,t){return e[T[t]||t]=i[t],e}),{}),c=e.type;switch("symbol"==typeof c?c=c.toString():r.warnOnInvalidChildren(e,o),c){case m.FRAGMENT:t=r.mapChildrenToProps(o,t);break;case m.LINK:case m.META:case m.NOSCRIPT:case m.SCRIPT:case m.STYLE:n=r.flattenArrayTypeChildren({child:e,arrayTypeChildren:n,newChildProps:s,nestedChildren:o});break;default:t=r.mapObjectTypeChildren({child:e,newProps:t,newChildProps:s,nestedChildren:o})}}})),this.mapArrayTypeChildrenToProps(n,t)},t.prototype.render=function(){var e=this.props,t=e.children,r=U(e,["children"]),n=Object.assign({},r);return t&&(n=this.mapChildrenToProps(t,n)),o.a.createElement(M.Consumer,null,(function(e){return o.a.createElement(q,Object.assign({},n,{context:e}))}))},t}(a["Component"]);K.propTypes={base:d.a.object,bodyAttributes:d.a.object,children:d.a.oneOfType([d.a.arrayOf(d.a.node),d.a.node]),defaultTitle:d.a.string,defer:d.a.bool,encodeSpecialCharacters:d.a.bool,htmlAttributes:d.a.object,link:d.a.arrayOf(d.a.object),meta:d.a.arrayOf(d.a.object),noscript:d.a.arrayOf(d.a.object),onChangeClientState:d.a.func,script:d.a.arrayOf(d.a.object),style:d.a.arrayOf(d.a.object),title:d.a.string,titleAttributes:d.a.object,titleTemplate:d.a.string},K.defaultProps={defer:!0,encodeSpecialCharacters:!0},K.displayName="Helmet";var z=r("Hx5s"),F=r("uJMD"),G=r("2n1B"),J=r("T+dw"),V=r("i0ey"),$=r("roml"),Q=r.n($),W=function(e){var t=e.route,r=void 0===t?{routes:[]}:t,a=r.routes,s=void 0===a?[]:a,c=e.children,u=e.location,l=void 0===u?{pathname:""}:u,p=Object(z["g"])(s),f=p.breadcrumb,d=Object(z["h"])(Object(n["a"])({pathname:l.pathname,formatMessage:F["m"],breadcrumb:f},e));return o.a.createElement(R,null,o.a.createElement(K,null,o.a.createElement("title",null,d),o.a.createElement("meta",{name:"description",content:d})),o.a.createElement("div",{className:Q.a.container},o.a.createElement("div",{className:Q.a.lang},Object(V["f"])()?o.a.createElement(G["a"],null):null),o.a.createElement("div",{className:Q.a.content},o.a.createElement("div",{className:Q.a.top},o.a.createElement("div",{className:Q.a.header},o.a.createElement(i["a"],{to:"/"},Object(V["e"])()?o.a.createElement("img",{alt:"logo",className:Q.a.logo,src:J["a"].getShareLogo()}):null,o.a.createElement("span",{className:Q.a.title},null==V["a"]?"\u672a\u8bbe\u7f6e\u540d\u79f0":V["a"].appName||"\u672a\u8bbe\u7f6e\u540d\u79f0"))),o.a.createElement("div",{className:Q.a.desc},null==V["a"]?"":V["a"].appDescription||"")),c),o.a.createElement(z["a"],{links:[],copyright:null==V["a"]?"":V["a"].copyright||""})))};t["default"]=Object(s["a"])((function(e){var t=e.settings;return Object(n["a"])({},t)}))(W)},roml:function(e,t,r){e.exports={container:"antd-pro-layouts-user-layout-container",lang:"antd-pro-layouts-user-layout-lang",content:"antd-pro-layouts-user-layout-content",top:"antd-pro-layouts-user-layout-top",header:"antd-pro-layouts-user-layout-header",logo:"antd-pro-layouts-user-layout-logo",title:"antd-pro-layouts-user-layout-title",desc:"antd-pro-layouts-user-layout-desc"}}}]);