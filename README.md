# alt

A Clojure library designed to provide alternatives in code.

This library is inspired by Patrick Stein's [Alternatives](https://github.com/nklein/alternatives)
in Common Lisp. As argued by Patrick, you may want to keep alternative code
at your fingertips to try out the alternatives later.

The use of macros in Lisp/Clojure means it will not harm the performance of
your code.

## Usage

Alternatives are tagged with keywords:

```clojure
(alternatives
  :a 1
  :b 2
  :c 3
)
```

By default, the first clause will be used unless any clause is tagged with
`:final`, which will be selected instead.

```clojure
(alternatives
  :a 1
  :b 2
  :final 3)
```

The `:final` clause can be overriden with a `:blessed` specification, though
the value of `:blessed` must be an existing tag, including `:final`:

```clojure
(alternatives
  :a 1
  :b 2
  :blessed :b
  :final 3)
```

The order of tags does not matter, but they have to be unique.

## License

Copyright © 2014 Yang Shouxun (yang.shx@fltrp.com)

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
