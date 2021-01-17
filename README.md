# Erobot

基于 [mirai](/mamoe/mirai) 框架实现的一个响应式 bot 框架.

## 功能
1. Help 打印帮助信息.
2. Echo 复读机.
3. Danbooru ID 查询和标签搜索能力.

## 待办事项
1. 全局级别的 (用户名密码代理) 配置, 准备用 SQLite, 目前直接写到代码里.
2. 群级别的配置, 准备用 SQLite. 唯一键大约是 {应用 (QQ / TG 等), 聊天类型 (私聊 / 群聊等), 聊天 id}:
  - 需要支持 NSFW 级别配置;
  - 支持 danbooru 账号配置;
  - 禁用某整个功能.
3. 还没完全解耦, 业务逻辑还需要理解 MessageChain. 理想状态是自己封装一个代理类.
4. 抽出 HTTP 请求逻辑, 提供下载超时自动重试能力.
5. Help 需要支持打印某个命令的详细帮助信息, 目前没有支持.
6. Danbooru 功能中支持 tag 搜索缩写和映射, 提供默认 tag 支持 (打字太多对手机用户不友好).
7. 图片处理:
  - 支持 Pixiv 的 ugoira 格式文件 (zipped jpegs);
  - 支持 GIF 处理;
  - 支持 MP4 处理;
  - 增加图片修改能力, 防腾讯屏蔽 (虽然目前看起来没有屏蔽);