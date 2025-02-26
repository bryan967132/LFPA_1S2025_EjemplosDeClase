/* Factorial.mj */

/* ------ HEADER ------- */
#include <stdio.h>

float heap[30101999];
float stack[30101999];
float P;
float H;
float t0, t1, t2, t3, t4, t5, t6, t7;

/* --- DECLARATIONS ---- */
void inicio();

/* ----- FUNCTIONS ----- */
void inicio() {
	/* ------- Declaración ------- */
	t0 = P + 1;
	stack[(int) t0] = 5;
	/* ----- Fin Declaración ----- */
	/* ------- Asignacion -------- */
	/* --------- Acceso ---------- */
	t1 = P + 1;
	t2 = stack[(int) t1];
	/* ------- Fin Acceso -------- */
	t3 = 3 * t2;
	t4 = 5 + t3;
	t5 = t4 - 10;
	t6 = P + 1;
	stack[(int) t6] = t5;
	/* ----- Fin Asignacion ------ */
L0:
	return;
}

/* ------- MAIN -------- */
int main() {
	P = 0;
	H = 0;
	/* ----- Llamada Funcion ----- */
	P = P + 0;
	inicio();
	t7 = stack[(int) P];
	P = P - 0;
	/* --- Fin Llamada Funcion --- */
	return 0;
}