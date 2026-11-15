package dev.kazael.kbdisplacement.mixin;

import dev.kazael.kbdisplacement.KnockbackConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(LivingEntity.class)
public abstract class LivingEntityKnockbackMixin {
    /**
     * Replaces vanilla knockback while retaining its basic damping and grounded lift.
     * The configured multipliers are applied independently to horizontal and vertical displacement.
     *
     * @author Knockback Displacement
     * @reason Adds independent configurable displacement scaling.
     */
    @Overwrite
    public void knockback(double strength, double x, double z) {
        KnockbackConfig config = KnockbackConfig.get();
        if (!config.enabled) return;

        LivingEntity self = (LivingEntity) (Object) this;
        if (!self.isPushable()) return;

        double horizontal = strength * config.horizontalMultiplier;
        double vertical = strength * config.verticalMultiplier;

        Vec3 direction = new Vec3(x, 0.0, z).normalize();
        Vec3 current = self.getDeltaMovement().multiply(0.5, 0.5, 0.5);
        Vec3 pushed = current.subtract(direction.scale(horizontal));

        if (self.onGround()) {
            pushed = pushed.add(0.0, vertical, 0.0);
        }
        self.setDeltaMovement(pushed);
        self.hasImpulse = true;
    }
}
