#version 410

in vec4 a_position;	

uniform mat4 u_modelMatrix;

void main() {
    gl_Position = u_modelMatrix * a_position;
}

