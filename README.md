
# Void Block Fabric 1.21.1

<p align='justify'>Simply adds a block that renders the void portal effect. Works for Fabric 1.21.1 and works with shaders! I will almost certainly never, ever be updating or changing this mod, because I'm genuinely surprised it works at all as it is, and I'm terrified that if I change ANYTHING inside it it'll break. (As in, I'm scared enough just adding this readme and pushing it)</p>

<p align='justify'>Required on both server and client. No dependencies.</p>

## Features

- Void Block - renders the End Gateway effect
- Shader compatibility - _NOTE: I recommend [Solas Shader](https://modrinth.com/shader/solas-shader) as it's one of the few I've come across that renders the End Portal effect screen-space aligned. Others don't, and so you can see the 'seams' where perpendicular surfaces built of void blocks meet (see screenshots)._ MASSIVE thanks to [fayer3](https://github.com/fayer3) for adding the shader support :)
- Redstone controllable
  Redstone control can be toggled on or off in the configs by setting

    ```"enableRedstoneControl": <true|false>```


## Credits

This mod started as a 1.21.1 port of LuminaSapphira's [Block of Sky](https://github.com/LuminaSapphira/block-of-sky), which itself was a port of [Literal Sky Block](https://github.com/nanite/Literal-Sky-Block) and used code from the Fabric port of that mod, [Literal Sky Block Fabric](https://github.com/Gaming32/literal-sky-block-fabric/). All of these projects are licensed under LGPL-3.0.

Shader support was very kindly added by [fayer3](https://github.com/fayer3) - MASSIVE thanks to them

## License

[GNU LGPLv3](https://choosealicense.com/licenses/gpl-3.0/)

_(I'm new to licences, so if I've messed this up please raise an issue and hopefully I'll see it at some point)_

