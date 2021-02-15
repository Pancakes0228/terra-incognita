package azmalent.terraincognita.proxy;

import azmalent.terraincognita.TerraIncognita;
import azmalent.terraincognita.client.ClientHandler;
import azmalent.terraincognita.client.gui.ModEditSignScreen;
import azmalent.terraincognita.common.tile.ModSignTileEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientProxy implements IProxy {
    public void spawnParticle(World world, IParticleData data, boolean alwaysRender, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        TerraIncognita.LOGGER.info("SPAWNING PARTICLE");
        world.addParticle(data, alwaysRender, x, y, z, xSpeed, ySpeed, zSpeed);
    }

    @Override
    public void openSignEditor(ModSignTileEntity sign) {
        ClientHandler.MC.displayGuiScreen(new ModEditSignScreen(sign));
    }
}
