WCFont
======

使用 [sfntly](https://code.google.com/p/sfntly/) 动态裁剪中文字体，让网页中嵌入中文字体成为可能！

# 使用方法

第一步，在服务器上准备好字体文件：`yourfont.ttf`（由于 sfntly 的限制，只支持 true-type 格式）。

第二步，引入 JavaScript 脚本：

```html
<h1>这是标题</h1>
<span class="title">这是副标题</span>

<script src="//example.com/?s=h1&family=yourfont"></script>
<script src="//example.com/?s=.title&family=yourfont"></script>
```

# 配置

## HTTP 服务器

您需要将放置字体的域名下的 `/fonts` 目录对应到您放置输出字体的目录。

## `conf/application.conf`

-  `font.input`：字体输入目录
-  `font.output`：字体输出目录
-  `url`：服务器的 URL 前缀，例如，当 URL 为 http://example.com 时，`@font-face` 中的 `url` 将会为 `http://example.com/fonts/generated-name.woff`。
