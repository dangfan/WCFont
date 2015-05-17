@(selector: String, family: String, url: String)

var elems = document.querySelectorAll('@selector');
var text = '';
for (var i = 0; i != elems.length; ++i) {
  text += elems[i].textContent;
}
var file = document.createElement('link');
file.setAttribute('rel', 'stylesheet');
file.setAttribute('type', 'text/css');
file.setAttribute('href', '@url/css?family=@family&content=' + text);
document.getElementsByTagName('head')[0].appendChild(file);
for (i = 0; i != elems.length; ++i) {
  elems[i].style.cssText = 'font-family:"@family"';
}