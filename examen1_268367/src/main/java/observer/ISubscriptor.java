/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package observer;

import modelo.ModeloPagarLuz;

/**
 *
 * @author Omar
 */
public interface ISubscriptor {
    void update(ModeloPagarLuz modelo);
}
