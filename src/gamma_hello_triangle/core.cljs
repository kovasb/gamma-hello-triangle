(ns gamma-hello-triangle.core
  (:require [gamma.api :as g]
            [gamma.program :as p]
            [goog.dom :as gdom]
            [goog.webgl :as ggl]))

(def vertex-position (g/attribute "a_VertexPosition" :vec2))

(def vertex-shader {(g/gl-position) (g/vec4 vertex-position 0 1)})

(def fragment-shader {(g/gl-frag-color) (g/vec4 1 0 0 1)})

(def hello-triangle
  (p/program
    {:vertex-shader vertex-shader
     :fragment-shader fragment-shader}))

(defn main []
  (let [gl  (.getContext (gdom/getElement "gl-canvas") "webgl")
        vs  (.createShader gl ggl/VERTEX_SHADER)
        fs  (.createShader gl ggl/FRAGMENT_SHADER)
        pgm (.createProgram gl)
        xs  (js/Float32Array. #js [-0.5 -0.5 0.5 -0.5 0 0])
        buf (.createBuffer gl)]
    (doto gl
      (.shaderSource vs (-> hello-triangle :vertex-shader :glsl))
      (.compileShader vs)
      (.shaderSource fs (-> hello-triangle :fragment-shader :glsl))
      (.compileShader fs)
      (.attachShader pgm vs)
      (.attachShader pgm fs)
      (.linkProgram pgm)
      (.bindBuffer ggl/ARRAY_BUFFER buf)
      (.bufferData ggl/ARRAY_BUFFER xs ggl/STATIC_DRAW)
      (.enableVertexAttribArray (.getAttribLocation gl pgm (:name vertex-position)))
      (.vertexAttribPointer (.getAttribLocation gl pgm (:name vertex-position))
        2 ggl/FLOAT false 0 0)
      (.useProgram pgm)
      (.drawArrays ggl/TRIANGLES 0 3))))

(comment
  (main)
  )