(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[2],{bmMU:function(e,t,n){"use strict";var r=Array.isArray,a=Object.keys,o=Object.prototype.hasOwnProperty,i="undefined"!==typeof Element;function s(e,t){if(e===t)return!0;if(e&&t&&"object"==typeof e&&"object"==typeof t){var n,c,u,l=r(e),p=r(t);if(l&&p){if(c=e.length,c!=t.length)return!1;for(n=c;0!==n--;)if(!s(e[n],t[n]))return!1;return!0}if(l!=p)return!1;var f=e instanceof Date,d=t instanceof Date;if(f!=d)return!1;if(f&&d)return e.getTime()==t.getTime();var h=e instanceof RegExp,m=t instanceof RegExp;if(h!=m)return!1;if(h&&m)return e.toString()==t.toString();var y=a(e);if(c=y.length,c!==a(t).length)return!1;for(n=c;0!==n--;)if(!o.call(t,y[n]))return!1;if(i&&e instanceof Element&&t instanceof Element)return e===t;for(n=c;0!==n--;)if(u=y[n],("_owner"!==u||!e.$$typeof)&&!s(e[u],t[u]))return!1;return!0}return e!==e&&t!==t}e.exports=function(e,t){try{return s(e,t)}catch(n){if(n.message&&n.message.match(/stack|recursion/i)||-2146828260===n.number)return console.warn("Warning: react-fast-compare does not handle circular references.",n.name,n.message),!1;throw n}}},mxmt:function(e,t,n){e.exports=n.p+"static/logo.51586d6b.svg"},"nsf+":function(e,t,n){"use strict";n.r(t);var r=n("XEnU"),a=n("Hx5s"),o=n("bmMU"),i=n.n(o),s=n("QLaP"),c=n.n(s),u=n("17x9"),l=n.n(u),p=n("q1tI"),f=n.n(p),d=n("Gytx"),h=n.n(d),m={BASE:"base",BODY:"body",HEAD:"head",HTML:"html",LINK:"link",META:"meta",NOSCRIPT:"noscript",SCRIPT:"script",STYLE:"style",TITLE:"title",FRAGMENT:"Symbol(react.fragment)"},y=Object.keys(m).map((function(e){return m[e]})),b={accesskey:"accessKey",charset:"charSet",class:"className",contenteditable:"contentEditable",contextmenu:"contextMenu","http-equiv":"httpEquiv",itemprop:"itemProp",tabindex:"tabIndex"},g=Object.keys(b).reduce((function(e,t){return e[b[t]]=t,e}),{}),T=function(e,t){for(var n=e.length-1;n>=0;n-=1){var r=e[n];if(Object.prototype.hasOwnProperty.call(r,t))return r[t]}return null},v=function(e){var t=T(e,m.TITLE),n=T(e,"titleTemplate");if(Array.isArray(t)&&(t=t.join("")),n&&t)return n.replace(/%s/g,(function(){return t}));var r=T(e,"defaultTitle");return t||r||void 0},O=function(e){return T(e,"onChangeClientState")||function(){}},A=function(e,t){return t.filter((function(t){return void 0!==t[e]})).map((function(t){return t[e]})).reduce((function(e,t){return Object.assign({},e,t)}),{})},C=function(e,t){return t.filter((function(e){return void 0!==e[m.BASE]})).map((function(e){return e[m.BASE]})).reverse().reduce((function(t,n){if(!t.length)for(var r=Object.keys(n),a=0;a<r.length;a+=1){var o=r[a].toLowerCase();if(-1!==e.indexOf(o)&&n[o])return t.concat(n)}return t}),[])},E=function(e,t,n){var r={};return n.filter((function(t){return!!Array.isArray(t[e])||(void 0!==t[e]&&console&&"function"==typeof console.warn&&console.warn("Helmet: "+e+' should be of type "Array". Instead found type "'+typeof t[e]+'"'),!1)})).map((function(t){return t[e]})).reverse().reduce((function(e,n){var a={};n.filter((function(e){for(var n,o=Object.keys(e),i=0;i<o.length;i+=1){var s=o[i],c=s.toLowerCase();-1===t.indexOf(c)||"rel"===n&&"canonical"===e[n].toLowerCase()||"rel"===c&&"stylesheet"===e[c].toLowerCase()||(n=c),-1===t.indexOf(s)||"innerHTML"!==s&&"cssText"!==s&&"itemprop"!==s||(n=s)}if(!n||!e[n])return!1;var u=e[n].toLowerCase();return r[n]||(r[n]={}),a[n]||(a[n]={}),!r[n][u]&&(a[n][u]=!0,!0)})).reverse().forEach((function(t){return e.push(t)}));for(var o=Object.keys(a),i=0;i<o.length;i+=1){var s=o[i],c=Object.assign({},r[s],a[s]);r[s]=c}return e}),[]).reverse()},j=function(e){return Array.isArray(e)?e.join(""):e},S=[m.NOSCRIPT,m.SCRIPT,m.STYLE],x=function(e,t){return void 0===t&&(t=!0),!1===t?String(e):String(e).replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/"/g,"&quot;").replace(/'/g,"&#x27;")},I=function(e){return Object.keys(e).reduce((function(t,n){var r=void 0!==e[n]?n+'="'+e[n]+'"':""+n;return t?t+" "+r:r}),"")},w=function(e,t){return void 0===t&&(t={}),Object.keys(e).reduce((function(t,n){return t[b[n]||n]=e[n],t}),t)},L=function(e,t,n){switch(e){case m.TITLE:return{toComponent:function(){return n=w(t.titleAttributes,{key:e=t.title,"data-rh":!0}),[f.a.createElement(m.TITLE,n,e)];var e,n},toString:function(){return function(e,n,r,a){var o=I(t.titleAttributes),i=j(n);return o?"<"+e+' data-rh="true" '+o+">"+x(i,a)+"</"+e+">":"<"+e+' data-rh="true">'+x(i,a)+"</"+e+">"}(e,t.title,0,n)}};case"bodyAttributes":case"htmlAttributes":return{toComponent:function(){return w(t)},toString:function(){return I(t)}};default:return{toComponent:function(){return function(e,t){return t.map((function(t,n){var r={key:n,"data-rh":!0};return Object.keys(t).forEach((function(e){var n=b[e]||e;"innerHTML"===n||"cssText"===n?r.dangerouslySetInnerHTML={__html:t.innerHTML||t.cssText}:r[n]=t[e]})),f.a.createElement(e,r)}))}(e,t)},toString:function(){return function(e,t,n){return t.reduce((function(t,r){var a=Object.keys(r).filter((function(e){return!("innerHTML"===e||"cssText"===e)})).reduce((function(e,t){var a=void 0===r[t]?t:t+'="'+x(r[t],n)+'"';return e?e+" "+a:a}),""),o=r.innerHTML||r.cssText||"",i=-1===S.indexOf(e);return t+"<"+e+' data-rh="true" '+a+(i?"/>":">"+o+"</"+e+">")}),"")}(e,t,n)}}}},P=function(e){var t=e.bodyAttributes,n=e.encode,r=e.htmlAttributes,a=e.linkTags,o=e.metaTags,i=e.noscriptTags,s=e.scriptTags,c=e.styleTags,u=e.title;void 0===u&&(u="");var l=e.titleAttributes;return{base:L(m.BASE,e.baseTag,n),bodyAttributes:L("bodyAttributes",t,n),htmlAttributes:L("htmlAttributes",r,n),link:L(m.LINK,a,n),meta:L(m.META,o,n),noscript:L(m.NOSCRIPT,i,n),script:L(m.SCRIPT,s,n),style:L(m.STYLE,c,n),title:L(m.TITLE,{title:u,titleAttributes:l},n)}},k=f.a.createContext({}),N=l.a.shape({setHelmet:l.a.func,helmetInstances:l.a.shape({get:l.a.func,add:l.a.func,remove:l.a.func})}),M="undefined"!=typeof document,H=function(e){function t(n){var r=this;e.call(this,n),this.instances=[],this.value={setHelmet:function(e){r.props.context.helmet=e},helmetInstances:{get:function(){return r.instances},add:function(e){r.instances.push(e)},remove:function(e){var t=r.instances.indexOf(e);r.instances.splice(t,1)}}},t.canUseDOM||(n.context.helmet=P({baseTag:[],bodyAttributes:{},encodeSpecialCharacters:!0,htmlAttributes:{},linkTags:[],metaTags:[],noscriptTags:[],scriptTags:[],styleTags:[],title:"",titleAttributes:{}}))}return e&&(t.__proto__=e),(t.prototype=Object.create(e&&e.prototype)).constructor=t,t.prototype.render=function(){return f.a.createElement(k.Provider,{value:this.value},this.props.children)},t}(p["Component"]);H.canUseDOM=M,H.propTypes={context:l.a.shape({helmet:l.a.shape()}),children:l.a.node.isRequired},H.defaultProps={context:{}},H.displayName="HelmetProvider";var R=function(e,t){var n,r=document.head||document.querySelector(m.HEAD),a=r.querySelectorAll(e+"[data-rh]"),o=[].slice.call(a),i=[];return t&&t.length&&t.forEach((function(t){var r=document.createElement(e);for(var a in t)Object.prototype.hasOwnProperty.call(t,a)&&("innerHTML"===a?r.innerHTML=t.innerHTML:"cssText"===a?r.styleSheet?r.styleSheet.cssText=t.cssText:r.appendChild(document.createTextNode(t.cssText)):r.setAttribute(a,void 0===t[a]?"":t[a]));r.setAttribute("data-rh","true"),o.some((function(e,t){return n=t,r.isEqualNode(e)}))?o.splice(n,1):i.push(r)})),o.forEach((function(e){return e.parentNode.removeChild(e)})),i.forEach((function(e){return r.appendChild(e)})),{oldTags:o,newTags:i}},D=function(e,t){var n=document.getElementsByTagName(e)[0];if(n){for(var r=n.getAttribute("data-rh"),a=r?r.split(","):[],o=[].concat(a),i=Object.keys(t),s=0;s<i.length;s+=1){var c=i[s],u=t[c]||"";n.getAttribute(c)!==u&&n.setAttribute(c,u),-1===a.indexOf(c)&&a.push(c);var l=o.indexOf(c);-1!==l&&o.splice(l,1)}for(var p=o.length-1;p>=0;p-=1)n.removeAttribute(o[p]);a.length===o.length?n.removeAttribute("data-rh"):n.getAttribute("data-rh")!==i.join(",")&&n.setAttribute("data-rh",i.join(","))}},_=function(e,t){var n=e.baseTag,r=e.htmlAttributes,a=e.linkTags,o=e.metaTags,i=e.noscriptTags,s=e.onChangeClientState,c=e.scriptTags,u=e.styleTags,l=e.title,p=e.titleAttributes;D(m.BODY,e.bodyAttributes),D(m.HTML,r),function(e,t){void 0!==e&&document.title!==e&&(document.title=j(e)),D(m.TITLE,t)}(l,p);var f={baseTag:R(m.BASE,n),linkTags:R(m.LINK,a),metaTags:R(m.META,o),noscriptTags:R(m.NOSCRIPT,i),scriptTags:R(m.SCRIPT,c),styleTags:R(m.STYLE,u)},d={},h={};Object.keys(f).forEach((function(e){var t=f[e],n=t.newTags,r=t.oldTags;n.length&&(d[e]=n),r.length&&(h[e]=f[e].oldTags)})),t&&t(),s(e,d,h)},q=null,Y=function(e){function t(){for(var t=[],n=arguments.length;n--;)t[n]=arguments[n];e.apply(this,t),this.rendered=!1}return e&&(t.__proto__=e),(t.prototype=Object.create(e&&e.prototype)).constructor=t,t.prototype.shouldComponentUpdate=function(e){return!h()(e,this.props)},t.prototype.componentDidUpdate=function(){this.emitChange()},t.prototype.componentWillUnmount=function(){this.props.context.helmetInstances.remove(this),this.emitChange()},t.prototype.emitChange=function(){var e,t,n=this.props.context,r=n.setHelmet,a=null,o=(e=n.helmetInstances.get().map((function(e){var t=Object.assign({},e.props);return delete t.context,t})),{baseTag:C(["href"],e),bodyAttributes:A("bodyAttributes",e),defer:T(e,"defer"),encode:T(e,"encodeSpecialCharacters"),htmlAttributes:A("htmlAttributes",e),linkTags:E(m.LINK,["rel","href"],e),metaTags:E(m.META,["name","charset","http-equiv","property","itemprop"],e),noscriptTags:E(m.NOSCRIPT,["innerHTML"],e),onChangeClientState:O(e),scriptTags:E(m.SCRIPT,["src","innerHTML"],e),styleTags:E(m.STYLE,["cssText"],e),title:v(e),titleAttributes:A("titleAttributes",e)});H.canUseDOM?(t=o,q&&cancelAnimationFrame(q),t.defer?q=requestAnimationFrame((function(){_(t,(function(){q=null}))})):(_(t),q=null)):P&&(a=P(o)),r(a)},t.prototype.init=function(){this.rendered||(this.rendered=!0,this.props.context.helmetInstances.add(this),this.emitChange())},t.prototype.render=function(){return this.init(),null},t}(p["Component"]);function B(e,t){var n={};for(var r in e)Object.prototype.hasOwnProperty.call(e,r)&&-1===t.indexOf(r)&&(n[r]=e[r]);return n}Y.propTypes={context:N.isRequired},Y.displayName="HelmetDispatcher";var U=function(e){function t(){e.apply(this,arguments)}return e&&(t.__proto__=e),(t.prototype=Object.create(e&&e.prototype)).constructor=t,t.prototype.shouldComponentUpdate=function(e){return!i()(this.props,e)},t.prototype.mapNestedChildrenToProps=function(e,t){if(!t)return null;switch(e.type){case m.SCRIPT:case m.NOSCRIPT:return{innerHTML:t};case m.STYLE:return{cssText:t};default:throw new Error("<"+e.type+" /> elements are self-closing and can not contain children. Refer to our API for more information.")}},t.prototype.flattenArrayTypeChildren=function(e){var t,n=e.child,r=e.arrayTypeChildren;return Object.assign({},r,((t={})[n.type]=(r[n.type]||[]).concat([Object.assign({},e.newChildProps,this.mapNestedChildrenToProps(n,e.nestedChildren))]),t))},t.prototype.mapObjectTypeChildren=function(e){var t,n,r=e.child,a=e.newProps,o=e.newChildProps,i=e.nestedChildren;switch(r.type){case m.TITLE:return Object.assign({},a,((t={})[r.type]=i,t),{titleAttributes:Object.assign({},o)});case m.BODY:return Object.assign({},a,{bodyAttributes:Object.assign({},o)});case m.HTML:return Object.assign({},a,{htmlAttributes:Object.assign({},o)});default:return Object.assign({},a,((n={})[r.type]=Object.assign({},o),n))}},t.prototype.mapArrayTypeChildrenToProps=function(e,t){var n=Object.assign({},t);return Object.keys(e).forEach((function(t){var r;n=Object.assign({},n,((r={})[t]=e[t],r))})),n},t.prototype.warnOnInvalidChildren=function(e,t){return c()(y.some((function(t){return e.type===t})),"function"==typeof e.type?"You may be attempting to nest <Helmet> components within each other, which is not allowed. Refer to our API for more information.":"Only elements types "+y.join(", ")+" are allowed. Helmet does not support rendering <"+e.type+"> elements. Refer to our API for more information."),c()(!t||"string"==typeof t||Array.isArray(t)&&!t.some((function(e){return"string"!=typeof e})),"Helmet expects a string as a child of <"+e.type+">. Did you forget to wrap your children in braces? ( <"+e.type+">{``}</"+e.type+"> ) Refer to our API for more information."),!0},t.prototype.mapChildrenToProps=function(e,t){var n=this,r={};return f.a.Children.forEach(e,(function(e){if(e&&e.props){var a=e.props,o=a.children,i=B(a,["children"]),s=Object.keys(i).reduce((function(e,t){return e[g[t]||t]=i[t],e}),{}),c=e.type;switch("symbol"==typeof c?c=c.toString():n.warnOnInvalidChildren(e,o),c){case m.FRAGMENT:t=n.mapChildrenToProps(o,t);break;case m.LINK:case m.META:case m.NOSCRIPT:case m.SCRIPT:case m.STYLE:r=n.flattenArrayTypeChildren({child:e,arrayTypeChildren:r,newChildProps:s,nestedChildren:o});break;default:t=n.mapObjectTypeChildren({child:e,newProps:t,newChildProps:s,nestedChildren:o})}}})),this.mapArrayTypeChildrenToProps(r,t)},t.prototype.render=function(){var e=this.props,t=e.children,n=B(e,["children"]),r=Object.assign({},n);return t&&(r=this.mapChildrenToProps(t,r)),f.a.createElement(k.Consumer,null,(function(e){return f.a.createElement(Y,Object.assign({},r,{context:e}))}))},t}(p["Component"]);U.propTypes={base:l.a.object,bodyAttributes:l.a.object,children:l.a.oneOfType([l.a.arrayOf(l.a.node),l.a.node]),defaultTitle:l.a.string,defer:l.a.bool,encodeSpecialCharacters:l.a.bool,htmlAttributes:l.a.object,link:l.a.arrayOf(l.a.object),meta:l.a.arrayOf(l.a.object),noscript:l.a.arrayOf(l.a.object),onChangeClientState:l.a.func,script:l.a.arrayOf(l.a.object),style:l.a.arrayOf(l.a.object),title:l.a.string,titleAttributes:l.a.object,titleTemplate:l.a.string},U.defaultProps={defer:!0,encodeSpecialCharacters:!0},U.displayName="Helmet";var K=n("9kvl"),F=n("55Ip"),G=n("2n1B"),W=n("mxmt"),J=n.n(W),$=n("roml"),Q=n.n($),X=function(e){var t=e.route,n=void 0===t?{routes:[]}:t,o=n.routes,i=void 0===o?[]:o,s=e.children,c=e.location,u=void 0===c?{pathname:""}:c,l=Object(a["f"])(i),p=l.breadcrumb,d=Object(a["g"])(Object(r["a"])({pathname:u.pathname,formatMessage:K["b"],breadcrumb:p},e));return f.a.createElement(H,null,f.a.createElement(U,null,f.a.createElement("title",null,d),f.a.createElement("meta",{name:"description",content:d})),f.a.createElement("div",{className:Q.a.container},f.a.createElement("div",{className:Q.a.lang},f.a.createElement(G["a"],null)),f.a.createElement("div",{className:Q.a.content},f.a.createElement("div",{className:Q.a.top},f.a.createElement("div",{className:Q.a.header},f.a.createElement(F["Link"],{to:"/"},f.a.createElement("img",{alt:"logo",className:Q.a.logo,src:J.a}),f.a.createElement("span",{className:Q.a.title},"Ant Design"))),f.a.createElement("div",{className:Q.a.desc},"Ant Design \u662f\u897f\u6e56\u533a\u6700\u5177\u5f71\u54cd\u529b\u7684 Web \u8bbe\u8ba1\u89c4\u8303")),s),f.a.createElement(a["a"],null)))};t["default"]=Object(K["a"])((function(e){var t=e.settings;return Object(r["a"])({},t)}))(X)},roml:function(e,t,n){e.exports={container:"antd-pro-layouts-user-layout-container",lang:"antd-pro-layouts-user-layout-lang",content:"antd-pro-layouts-user-layout-content",top:"antd-pro-layouts-user-layout-top",header:"antd-pro-layouts-user-layout-header",logo:"antd-pro-layouts-user-layout-logo",title:"antd-pro-layouts-user-layout-title",desc:"antd-pro-layouts-user-layout-desc"}}}]);